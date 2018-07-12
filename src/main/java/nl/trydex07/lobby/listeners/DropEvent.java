package nl.trydex07.lobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class DropEvent implements Listener{

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerPickupItemEvent e){
        e.setCancelled(true);
    }
}
