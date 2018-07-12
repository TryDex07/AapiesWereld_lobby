package nl.trydex07.lobby.listeners;


import be.iFlyinqMC.Core.Level.LevelManager;
import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.particles.Circle;
import nl.trydex07.lobby.particles.Helix;
import nl.trydex07.lobby.particles.Music;
import nl.trydex07.lobby.menus.Particle;
import nl.trydex07.lobby.menus.banners.Bannermenu;
import nl.trydex07.lobby.menus.heads;
import nl.trydex07.lobby.mysql.database.SQL;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.ArrayList;
import java.util.List;

public class ClickEvent implements Listener{

    private FileManager fm = new FileManager(Core.getPlugin(Core.class), "SelectorItems.yml");
    private FileManager sel1 = new FileManager(Core.getPlugin(Core.class), "SelectorSkulls.yml");
    private List<String> list;
    private List<String> list1;

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getType() == InventoryType.CREATIVE || e.getInventory().getType() == InventoryType.PLAYER || e.getInventory().getType() == InventoryType.CRAFTING){
            e.setCancelled(true);
        }
        if(e.getInventory().getName().equalsIgnoreCase(Utility.format("&4Particle"))) {
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if(!p.hasPermission("lobby.particle.slot." + e.getSlot())){
                e.setCancelled(true);
                p.sendMessage(Utility.format(Utility.getMessage("NoPerm")));
            }else {

                if(e.getSlot() == 10){
                    if(Core.hash.get(p.getUniqueId()) != null) {
                        Bukkit.getScheduler().cancelTask(Core.hash.get(p.getUniqueId()));
                    }
                    new Circle().createCircle(p);
                }if(e.getSlot() == 12){
                    if(Core.hash.get(p.getUniqueId()) != null) {
                        Bukkit.getScheduler().cancelTask(Core.hash.get(p.getUniqueId()));
                    }
                    new Helix().createHelix(p);
                }if(e.getSlot() == 14){
                    if(Core.hash.get(p.getUniqueId()) != null) {
                        Bukkit.getScheduler().cancelTask(Core.hash.get(p.getUniqueId()));
                    }
                    new Music().sendParticle(p, Effect.HEART);
                }if(e.getSlot() == 16){
                    if(Core.hash.get(p.getUniqueId()) != null) {
                        Bukkit.getScheduler().cancelTask(Core.hash.get(p.getUniqueId()));
                    }
                    new Music().sendParticle(p, Effect.NOTE);
                }

                    e.setCancelled(true);

                    SQL.setString(p, "Lobby", "particle", ""+ + e.getSlot());
                    p.closeInventory();
            }
            }
        if(e.getInventory().getName().equalsIgnoreCase(Utility.format("&4Heads"))) {
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
                if(!p.hasPermission("lobby.heads.slot." + e.getSlot())){
                    e.setCancelled(true);
                    p.sendMessage(Utility.format(Utility.getMessage("NoPerm")));
                }else {
                    if (e.getSlot() == 19) {
                        e.setCancelled(true);
                        if (LevelManager.getLevel(p) == 100) {
                            SQL.setString(p, "Lobby", "head", "010"+ + e.getSlot());
                            p.getInventory().setHelmet(e.getCurrentItem());
                            p.closeInventory();
                        }
                    } else if (e.getSlot() == 20 || e.getSlot() == 21 || e.getSlot() == 22) {
                        e.setCancelled(true);
                        if (LevelManager.getLevel(p) == 250) {
                            SQL.setString(p, "Lobby", "head", "010"+ + e.getSlot());
                            p.getInventory().setHelmet(e.getCurrentItem());
                            p.closeInventory();
                        }
                    } else {

                        e.setCancelled(true);

                        SQL.setString(p, "Lobby", "head", "010"+ + e.getSlot());
                        p.getInventory().setHelmet(e.getCurrentItem());
                        p.closeInventory();
                    }
                }
        } else if(e.getInventory().getName().equalsIgnoreCase(Utility.format("&4Banners"))) {
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if(!p.hasPermission("lobby.banners.slot." + e.getSlot())){
                e.setCancelled(true);
                p.sendMessage(Utility.format(Utility.getMessage("NoPerm")));
            }else {
                    e.setCancelled(true);
                SQL.setString(p, "Lobby", "head", "020"+ + e.getSlot());
                    p.getInventory().setHelmet(e.getCurrentItem());
                    p.closeInventory();

            }
        }else if(e.getInventory().getName().equalsIgnoreCase(Utility.format("&4Cosmetics"))) {
            e.setCancelled(true);
            if(e.getSlot() == 24){
                p.getInventory().setArmorContents(null);
                SQL.setString(p, "Lobby", "head", "none");
                SQL.setString(p, "Lobby", "particle", "none");
                if(Core.hash.get(p.getUniqueId()) != null) {
                    Bukkit.getScheduler().cancelTask(Core.hash.get(p.getUniqueId()));
                }
            }
            if(e.getSlot() == 20){
                new Particle().setup(p);
            }
            if(e.getSlot() == 15){
                new heads().setup(p);
            }
            if(e.getSlot() == 11){
                new Bannermenu().setup(p);
            }
        } else if(e.getInventory().getName().equalsIgnoreCase(Utility.format("&4Server Selector"))){
            e.setCancelled(true);
            addToList();
            fm.reloadConfig();
            sel1.reloadConfig();
            for(String l : list) {
                if (e.getSlot() == fm.getConfig().getInt("Selector." + l + ".slot")) {
                    if (fm.getConfig().getString("Selector." + l + ".clickable").equalsIgnoreCase("true")) {
                        Utility.send(p, fm.getConfig().getString("Selector." + l + ".serverName"));
                        p.closeInventory();
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
            addToList1();
                for(String ll : list1) {
                    if (e.getSlot() == sel1.getConfig().getInt("skulls." + ll + ".slot")){
                        if (sel1.getConfig().getString("skulls." + ll + ".clickable").equalsIgnoreCase("true")) {
                            Utility.send(p, sel1.getConfig().getString("skulls." + ll + ".serverName"));
                            p.closeInventory();
                        }else{
                            e.setCancelled(true);
                        }
                    }
                }

        }
    }

    public void addToList(){
        list = new ArrayList<>();
        for(String l : fm.getConfig().getConfigurationSection("Selector").getKeys(false)){
            list.add(l);
        }
    }
    public void addToList1(){
        list1 = new ArrayList<>();
        for(String l : sel1.getConfig().getConfigurationSection("skulls").getKeys(false)){
            list1.add(l);
        }
    }
}
