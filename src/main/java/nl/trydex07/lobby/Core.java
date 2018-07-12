package nl.trydex07.lobby;

import nl.trydex07.lobby.commands.CMDCompleter;
import nl.trydex07.lobby.commands.CMDHandler;
import nl.trydex07.lobby.commands.CMD_Crate;
import nl.trydex07.lobby.handlers.crate.ArmorStandHandler;
import nl.trydex07.lobby.handlers.crate.CrateHandler;
import nl.trydex07.lobby.listeners.*;
import nl.trydex07.lobby.mysql.basic.Config;
import nl.trydex07.lobby.mysql.database.MySQL;
import nl.trydex07.lobby.mysql.database.SQL;
import nl.trydex07.lobby.parkour.ParkourHandler;
import nl.trydex07.lobby.parkour.commands.CMD_Parkour;
import nl.trydex07.lobby.parkour.listeners.MoveEvent;
import nl.trydex07.lobby.utilitys.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Core extends JavaPlugin{

    public static HashMap<UUID, Integer> hash = new HashMap<>();


    FileManager fm = new FileManager(this, "CheckPoints.yml");

    public void onEnable(){
        mysql();
        configs();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getCommand("lobby").setExecutor(new CMDHandler());
        getCommand("lobby").setTabCompleter(new CMDCompleter());
        getCommand("crate").setExecutor( new CMD_Crate());
        getCommand("parkour").setExecutor( new CMD_Parkour());

        PluginManager pm  = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new InteractEvent(), this);
        pm.registerEvents(new ClickEvent(), this);
        pm.registerEvents(new DropEvent(), this);
        pm.registerEvents(new InteractCrateEvent(), this);
        pm.registerEvents(new BreakEvent(), this);
        pm.registerEvents(new ParticleJoin(), this);
        pm.registerEvents(new QuitEvent(), this);
        pm.registerEvents(new nl.trydex07.lobby.parkour.listeners.BreakEvent(), this);
        pm.registerEvents(new MoveEvent(), this);

        new CrateHandler().init();
        new ArmorStandHandler().init();
        ParkourHandler.init();
        for(String name : fm.getConfig().getConfigurationSection("CheckPoints").getKeys(false)) {
            new ParkourHandler(name);
        }
    }

    public void onDisable(){
        for (ArmorStand a : ArmorStandHandler.getArmorStands()) {
            a.remove();
        }
        for(Item i : JoinEvent.getArmorStands()){
            i.remove();
        }
    }

    private void mysql(){
        Config.create();
        MySQL.connect();
        SQL.createTable("Lobby", "uuid varchar(255), player text, toggleplayer text, fly text, particle text, head varchar(900)");
        SQL.createTable("Crates", "uuid varchar(255), player text, BananenKey text");

        new BukkitRunnable(){
            @Override
            public void run() {
                MySQL.disconnect();
                MySQL.connect();
            }
        }.runTaskTimer(Core.getPlugin(Core.class), 20 * 60 * 5,20 * 60 * 5);

    }

    private void configs(){
        new FileManager(this, "Messages.yml");
        new FileManager(this, "ArmorStands.yml");
        new FileManager(this, "Config.yml");
        new FileManager(this, "SelectorItems.yml");
        new FileManager(this, "SelectorSkulls.yml");
        new FileManager(this, "Crates.yml");
        new FileManager(this, "CrateItems.yml");
        new FileManager(this, "CheckPoints.yml");


    }
}
