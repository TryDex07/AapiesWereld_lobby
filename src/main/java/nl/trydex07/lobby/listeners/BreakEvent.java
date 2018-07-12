package nl.trydex07.lobby.listeners;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.utilitys.FileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class BreakEvent implements Listener{

    List<String> list;
    String world;
    double x, y, z;
    FileManager fm = new FileManager(Core.getPlugin(Core.class), "Crates.yml");


    @EventHandler
    public void onbreak(BlockBreakEvent e){
        e.setCancelled(true);
        fm.reloadConfig();
        registerCates();

        for(String crate : list){
            world = fm.getConfig().getString("Crates." + crate + ".world");
            x = fm.getConfig().getDouble("Crates." + crate + ".x");
            y = fm.getConfig().getDouble("Crates." + crate + ".y");
            z = fm.getConfig().getDouble("Crates." + crate + ".z");

            if(e.getBlock().getLocation().getX() == x){
                if(e.getBlock().getLocation().getY() == y){
                    if(e.getBlock().getLocation().getZ() == z){
                        if(e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase(world)){
                            e.setCancelled(true);
                        }

                    }
                }

            }
        }
    }

    @EventHandler
    public void onm(BlockPlaceEvent e){
        e.setCancelled(true);
    }
    public void registerCates() {
        list = new ArrayList<>();
        for (String section : fm.getConfig().getConfigurationSection("Crates").getKeys(false)) {
            list.add(section);
        }
    }
}
