package nl.trydex07.lobby.parkour.listeners;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.mysql.database.SQL;
import nl.trydex07.lobby.parkour.ParkourHandler;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MoveEvent implements Listener {

    public static HashMap<UUID, String> hash = new HashMap<>();
    private String name;


    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();


        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {

            for (String ParName : ParkourHandler.getNames()) {
                Location loc = ParkourHandler.getParkour(ParName).getStart();
                if (((loc.getBlockX() == e.getTo().getBlockX()) && (loc.getBlockY() == e.getTo().getBlockY() - 1) && (loc.getBlockZ() == e.getTo().getBlockZ()) && (loc.getWorld().getName().equalsIgnoreCase(e.getTo().getWorld().getName())))) {


                    this.name = ParName;
                    p.sendMessage(Utility.format(Utility.getMessage("parkourstart")));
                    hash.put(p.getUniqueId(), name);
                    ParkourHandler.getParkour(hash.get(p.getUniqueId())).setIngame(p.getUniqueId(), true);
                    p.getInventory().clear();
                    ItemStack bar = new ItemHandler().getItem(Material.BARRIER, 1, 0, Utility.format("&4&lStop Parkour"));
                    p.getInventory().setItem(4, bar);
                    p.setFlying(false);
                }
            }
            if(hash.get(p.getUniqueId()) != null || ParkourHandler.getParkour(hash.get(p.getUniqueId())) != null) {
                if (ParkourHandler.getParkour(hash.get(p.getUniqueId())).isInGame(p.getUniqueId())) {
                    for (Location loc : ParkourHandler.getParkour(hash.get(p.getUniqueId())).getCheckpoints()) {

                        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
                            if (((loc.getBlockX() == e.getTo().getBlockX()) && (loc.getBlockY() == e.getTo().getBlockY() - 1) && (loc.getBlockZ() == e.getTo().getBlockZ()) && (loc.getWorld().getName().equalsIgnoreCase(e.getTo().getWorld().getName())))) {

                                p.sendMessage(Utility.format(Utility.getMessage("parkourcheckpoint")));
                                ParkourHandler.getParkour(hash.get(p.getUniqueId())).setCheckpoint(p.getUniqueId(), p.getLocation());

                            }
                        }
                    }
                }
            }
        }
        FileManager parkour = new FileManager(Core.getPlugin(Core.class), "Config.yml");

        if(hash.get(p.getUniqueId()) != null || ParkourHandler.getParkour(hash.get(p.getUniqueId())) != null) {
            if (ParkourHandler.getParkour(hash.get(p.getUniqueId())).isInGame(p.getUniqueId())) {

                for (String ParName : ParkourHandler.getNames()) {
                    Location loc = ParkourHandler.getParkour(ParName).getFinish();

                    if (e.getTo().distanceSquared(e.getFrom()) > 0) {
                        if (e.getFrom().distanceSquared(e.getTo()) > 0.0D) {
                            if (((loc.getBlockX() == e.getTo().getBlockX()) && (loc.getBlockY() == e.getTo().getBlockY() - 1) && (loc.getBlockZ() == e.getTo().getBlockZ()) && (loc.getWorld().getName().equalsIgnoreCase(e.getTo().getWorld().getName())))) {

                                p.sendMessage(Utility.format(Utility.getMessage("parkourfinish")));
                                ParkourHandler.getParkour(hash.get(p.getUniqueId())).removeCheckPoint(p.getUniqueId());
                                ParkourHandler.getParkour(hash.get(p.getUniqueId())).setIngame(p.getUniqueId(), false);
                                hash.remove(p.getUniqueId());
                                e.getPlayer().getInventory().clear();
                                giveItems(e.getPlayer());
                                Location loc1 = (Location) parkour.getConfig().get("parkourspawn");
                                if (loc1 != null) {
                                    e.getPlayer().teleport(loc1);
                                } else {
                                    e.getPlayer().sendMessage(Utility.format(Utility.getMessage("parkourspawnnull")));
                                }
                                String s = SQL.getString(e.getPlayer(), "fly");
                                if (s.equalsIgnoreCase("true")) {
                                    p.setAllowFlight(true);
                                    p.setFlying(true);
                                } else {
                                    p.setAllowFlight(false);
                                    p.setFlying(false);
                                }
                            }
                        }
                    }

                }
            }
        }

        if (p.isFlying()) {
            if (ParkourHandler.getParkour(hash.get(p.getUniqueId())) != null) {
                if (ParkourHandler.getParkour(hash.get(p.getUniqueId())).isInGame(p.getUniqueId())) {
                    p.sendMessage(Utility.format(Utility.getMessage("parkourcanceld")));
                    ParkourHandler.getParkour(hash.get(p.getUniqueId())).removeCheckPoint(p.getUniqueId());
                    ParkourHandler.getParkour(hash.get(p.getUniqueId())).setIngame(p.getUniqueId(), false);
                    hash.remove(p.getUniqueId());
                    e.getPlayer().getInventory().clear();
                    giveItems(e.getPlayer());
                    String s = SQL.getString(e.getPlayer(), "fly");
                    if (s.equalsIgnoreCase("true")) {
                        p.setAllowFlight(true);
                        p.setFlying(true);
                    } else {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                    }
                }
            }
        }
        if(hash.get(p.getUniqueId()) != null || ParkourHandler.getParkour(hash.get(p.getUniqueId())) != null) {
            if (ParkourHandler.getParkour(hash.get(p.getUniqueId())).isInGame(p.getUniqueId())) {
                if (ParkourHandler.getParkour(hash.get(p.getUniqueId())).getCheckPoint(p.getUniqueId()) != null) {
                    if (ParkourHandler.getParkour(hash.get(p.getUniqueId())).getCheckPoint(p.getUniqueId()).getY() - 1.5 > p.getLocation().getY()) {
                        if (p.getUniqueId() == null || ParkourHandler.getParkour(name).getCheckPoint(p.getUniqueId()) == null)
                            return;
                        p.setFallDistance(0);
                        p.teleport(ParkourHandler.getParkour(name).getCheckPoint(p.getUniqueId()));
                    }
                }
            }
        }
    }

    public void giveItems(Player p) {
        List<String> compass = new ArrayList<>();
        compass.add(Utility.format("&7Kies de game die jij het liefst wilt spelen."));
        new ItemHandler().addItem(p, Material.COMPASS, 1, 0, Utility.format("&4Server Selector &7(Rechtsklik)"), compass, 0);
        List<String> toggle = new ArrayList<>();
        toggle.add(Utility.format("&7Klik hier om alle spelers te verbergen!"));
        new ItemHandler().addItem(p, Material.RED_ROSE,1, 8, Utility.format("&4Toggle Spelers &7(Rechtsklik)"), toggle,3);
        List<String> profile = new ArrayList<>();
        profile.add(Utility.format("&7Hier kan je je eigen stats bekijken!"));
        new ItemHandler().addPlayerProfile(p,Utility.format("&4Mijn Profiel &7(Rechtsklik)"),profile,8);
        List<String> cosmetic = new ArrayList<>();
        cosmetic.add(Utility.format("&7Klik hier om het cosmetic menu te openen."));
        new ItemHandler().addItem(p, Material.ENDER_CHEST,1, 0, Utility.format("&4Cosmetics &7(Rechtsklik)"), cosmetic,5);
        List<String> fly = new ArrayList<>();
        fly.add(Utility.format("&7Met deze veer kan je vliegen aan en uit zetten."));
        new ItemHandler().addItem(p, Material.FEATHER,1, 0, Utility.format("&4Toggle Fly &7(Rechtsklik)"), fly,4);
    }
}

