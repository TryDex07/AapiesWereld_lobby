package nl.trydex07.lobby.listeners;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.menus.Cosmetics;
import nl.trydex07.lobby.menus.Selector;
import nl.trydex07.lobby.mysql.database.SQL;
import nl.trydex07.lobby.parkour.ParkourHandler;
import nl.trydex07.lobby.parkour.listeners.MoveEvent;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class InteractEvent implements Listener{

    private FileManager parkour = new FileManager(Core.getPlugin(Core.class), "Config.yml");

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getItem() == null){
                return;
            }
            if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null){
                return;
            }
            if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Utility.format("&6&lStop Parkour"))) {
                parkour.reloadConfig();
                ParkourHandler.getParkour(MoveEvent.hash.get(e.getPlayer().getUniqueId())).removeCheckPoint(e.getPlayer().getUniqueId());
                ParkourHandler.getParkour(MoveEvent.hash.get(e.getPlayer().getUniqueId())).setIngame(e.getPlayer().getUniqueId(), false);
                MoveEvent.hash.remove(e.getPlayer().getUniqueId());
                e.getPlayer().sendMessage(Utility.format(Utility.getMessage("parkourstopt")));
                e.getPlayer().getInventory().clear();
                giveItems(e.getPlayer());
                String s = SQL.getString(e.getPlayer(), "fly");
                if (s.equalsIgnoreCase("true")) {
                    e.getPlayer().setAllowFlight(true);
                    e.getPlayer().setFlying(true);
                } else {
                    e.getPlayer().setAllowFlight(false);
                    e.getPlayer().setFlying(false);
                }
                Location loc = (Location) parkour.getConfig().get("parkourspawn");
                if(loc != null) {
                    e.getPlayer().teleport(loc);
                }else{
                    e.getPlayer().sendMessage(Utility.format(Utility.getMessage("parkourspawnnull")));
                }
            }else if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Utility.format("&6Server Selector &7(Rechtsklik)"))){
                new Selector().open(e.getPlayer());
            }else if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Utility.format("&6Toggle Spelers &7(Rechtsklik)"))) {
                String s = SQL.getString(e.getPlayer(), "toggleplayer");
                if(s.equalsIgnoreCase("true")){
                    for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                        e.getPlayer().hidePlayer(onlinePlayer);
                    }
                    SQL.setString(e.getPlayer(), "Lobby",  "toggleplayer", "false");
                    e.getPlayer().sendMessage(Utility.getMessage("Hide-player").replace("{player}", "" + e.getPlayer()));
                }else{
                    for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                        e.getPlayer().showPlayer(onlinePlayer);
                    }
                    SQL.setString(e.getPlayer(), "Lobby",  "toggleplayer", "true");
                    e.getPlayer().sendMessage(Utility.getMessage("Show-player").replace("{player}", "" + e.getPlayer()));
                }
            }else if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Utility.format("&6Toggle Fly &7(Rechtsklik)"))) {
                if(!e.getPlayer().hasPermission("fly.use")){
                    e.getPlayer().sendMessage(Utility.format(Utility.getMessage("NoPerm")));
                }else {
                    String s = SQL.getString(e.getPlayer(), "fly");
                    if (s.equalsIgnoreCase("true")) {
                        SQL.setString(e.getPlayer(), "Lobby", "fly", "false");
                        e.getPlayer().setAllowFlight(false);
                        e.getPlayer().setFlying(false);
                        e.getPlayer().sendMessage(Utility.getMessage("Disable-fly").replace("{player}", "" + e.getPlayer()));
                    } else {
                        SQL.setString(e.getPlayer(), "Lobby", "fly", "true");
                        e.getPlayer().setAllowFlight(true);
                        e.getPlayer().setFlying(true);
                        e.getPlayer().sendMessage(Utility.getMessage("Enable-fly").replace("{player}", "" + e.getPlayer()));
                    }
                }
            }else if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Utility.format("&6Cosmetics &7(Rechtsklik)"))) {
                new Cosmetics().setup(e.getPlayer());
            }else{
                return;
            }
        }
    }

    public void giveItems(Player p) {
        List<String> compass = new ArrayList<>();
        compass.add(Utility.format("&7Kies de game die jij het liefst wilt spelen."));
        new ItemHandler().addItem(p, Material.COMPASS, 1, 0, Utility.format("&6Server Selector &7(Rechtsklik)"), compass, 0);
        List<String> toggle = new ArrayList<>();
        toggle.add(Utility.format("&7Klik hier om alle spelers te verbergen!"));
        new ItemHandler().addItem(p, Material.RED_ROSE,1, 8, Utility.format("&6Toggle Spelers &7(Rechtsklik)"), toggle,3);
        List<String> profile = new ArrayList<>();
        profile.add(Utility.format("&7Hier kan je je eigen stats bekijken!"));
        new ItemHandler().addPlayerProfile(p,Utility.format("&6Mijn Profiel &7(&cOnderhoud&7)"),profile,8);
        List<String> cosmetic = new ArrayList<>();
        cosmetic.add(Utility.format("&7Klik hier om het cosmetic menu te openen."));
        new ItemHandler().addItem(p, Material.ENDER_CHEST,1, 0, Utility.format("&6Cosmetics &7(Rechtsklik)"), cosmetic,5);
        List<String> fly = new ArrayList<>();
        fly.add(Utility.format("&7Met deze veer kan je vliegen aan en uit zetten."));
        new ItemHandler().addItem(p, Material.FEATHER,1, 0, Utility.format("&6Toggle Fly &7(Rechtsklik)"), fly,4);
    }
}
