package nl.trydex07.lobby.listeners;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.handlers.crate.ArmorStandHandler;
import nl.trydex07.lobby.particles.Circle;
import nl.trydex07.lobby.particles.Helix;
import nl.trydex07.lobby.particles.Music;
import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.mysql.database.MySQL;
import nl.trydex07.lobby.mysql.database.SQL;
import nl.trydex07.lobby.reflection.Title;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class JoinEvent implements Listener {

    FileManager fm = new FileManager(Core.getPlugin(Core.class), "Config.yml");
    public static HashMap<Item, String> hash = new HashMap<>();

    public static List<Item> getArmorStands() {
        List<Item> a = new ArrayList<>();
        for (Map.Entry<Item, String> entry : hash.entrySet()) {
            a.add(entry.getKey());
        }
        return a;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        if (playerExists(p.getUniqueId(), "Crates") != true) {
            SQL.insertData("uuid, player, BananenKey", " '" + p.getUniqueId().toString() + "', '" + p.getName() + "', '0'", "Crates");

        } else {

        }
        if (playerExists(p.getUniqueId(), "Lobby") != true) {
            int i = fm.getConfig().getInt("joined");
            fm.getConfig().set("joined", i + 1);
            fm.saveConfig();
            int count = fm.getConfig().getInt("joined");
            new Title().sendTitle(p, Utility.getMessage("Title").replace("{count}", "" + count), Utility.getMessage("Subtitle").replace("{count}", "" + count), 10, 20, 10);
            setFirework(p);
            setFirework(p);
            setFirework(p);
            SQL.insertData("uuid, player, toggleplayer, fly, particle, head", " '" + p.getUniqueId().toString() + "', '" + p.getName() + "', 'true', 'false', 'none', 'none'", "Lobby");
        } else {
        }


        for (Player all : Bukkit.getOnlinePlayers()) {
            String s1 = SQL.getString(all, "toggleplayer");
            if (s1.equalsIgnoreCase("false")) {
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    if (!(onlinePlayer == all)) {
                        all.hidePlayer(onlinePlayer);
                    }
                }
            } else {
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    if (!(onlinePlayer == all)) {
                        all.showPlayer(onlinePlayer);
                    }
                }
            }
        }
        String s = SQL.getString(e.getPlayer(), "fly");
        if (s.equalsIgnoreCase("true")) {
            p.setAllowFlight(true);
            p.setFlying(true);
        } else {
            p.setAllowFlight(false);
            p.setFlying(false);
        }
        String s1 = SQL.getString(e.getPlayer(), "toggleplayer");
        if (s1.equalsIgnoreCase("false")) {
            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                p.hidePlayer(onlinePlayer);
            }
        } else {
            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                p.showPlayer(onlinePlayer);
            }
        }

        giveItems(p);
        String particle = SQL.getString(e.getPlayer(), "particle");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (p.isOnline() == false) {
                    cancel();
                    return;
                }
                Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY()+ p.getEyeHeight(), p.getLocation().getZ());
                loc = gerRadius(loc);
                Item i = p.getWorld().dropItem(loc, new ItemStack(Material.GOLD_INGOT));
                i.setVelocity(new Vector(0, 0, 0));
                i.setFallDistance(0);
                i.setCustomNameVisible(true);
                i.setCustomName(Utility.format(Utility.format("&c&lYOUTUBE &fMONEY")));

                hash.put(i,"gg");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        i.remove();
                    }
                }.runTaskLater(Core.getPlugin(Core.class), 60);
            }
        }.runTaskTimer(Core.getPlugin(Core.class), 10, 10);

        if (particle.equalsIgnoreCase("none")) {
            return;
        } else if (particle.equalsIgnoreCase("10")) {
            new Circle().createCircle(p);
            Core.hash.put(p.getUniqueId(), Circle.runnable);
        } else if (particle.equalsIgnoreCase("12")) {
            new Helix().createHelix(p);
        } else if (particle.equalsIgnoreCase("14")) {
            new Music().sendParticle(p, Effect.HEART);
        } else if (particle.equalsIgnoreCase("16")) {
            new Music().sendParticle(p, Effect.NOTE);
        }


    }

    public void giveItems(Player p) {
        List<String> compass = new ArrayList<>();
        compass.add(Utility.format("&7Kies de game die jij het liefst wilt spelen."));
        new ItemHandler().addItem(p, Material.COMPASS, 1, 0, Utility.format("&4Server Selector &7(Rechtsklik)"), compass, 0);
        List<String> toggle = new ArrayList<>();
        toggle.add(Utility.format("&7Klik hier om alle spelers te verbergen!"));
        new ItemHandler().addItem(p, Material.RED_ROSE, 1, 8, Utility.format("&4Toggle Spelers &7(Rechtsklik)"), toggle, 3);
        List<String> profile = new ArrayList<>();
        profile.add(Utility.format("&7Hier kan je je eigen stats bekijken!"));
        new ItemHandler().addPlayerProfile(p, Utility.format("&4Mijn Profiel &7(Rechtsklik)"), profile, 8);
        List<String> cosmetic = new ArrayList<>();
        cosmetic.add(Utility.format("&7Klik hier om het cosmetic menu te openen."));
        new ItemHandler().addItem(p, Material.ENDER_CHEST, 1, 0, Utility.format("&4Cosmetics &7(Rechtsklik)"), cosmetic, 5);
        List<String> fly = new ArrayList<>();
        fly.add(Utility.format("&7Met deze veer kan je vliegen aan en uit zetten."));
        new ItemHandler().addItem(p, Material.FEATHER, 1, 0, Utility.format("&4Toggle Fly &7(Rechtsklik)"), fly, 4);
    }

    public Location gerRadius(Location loc){
        double radius = 1.3;
        Random r = ThreadLocalRandom.current();
        return loc.clone().add(r.nextGaussian() * radius,0,r.nextGaussian() * radius);
    }

    public boolean playerExists(UUID uuid, String table) {
        try {
            PreparedStatement statement = MySQL.con.prepareStatement("SELECT * FROM " + "`" + table + "`" + "WHERE `uuid` = ?");
            statement.setString(1, uuid.toString());

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean setFirework(Player player) {
        Firework f = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
        FireworkMeta meta = f.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder()
                .flicker(false)
                .trail(true)
                .with(FireworkEffect.Type.BALL_LARGE)
                .withColor(Color.RED)
                .withColor(Color.BLUE)
                .withFade(Color.GREEN)
                .build());
        f.setFireworkMeta(meta);
        f.eject();
        return false;
    }
}
