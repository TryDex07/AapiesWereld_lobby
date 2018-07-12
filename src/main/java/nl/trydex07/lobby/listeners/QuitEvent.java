package nl.trydex07.lobby.listeners;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.parkour.ParkourHandler;
import nl.trydex07.lobby.parkour.listeners.MoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener{

    @EventHandler
    public void on(PlayerQuitEvent e){
        e.setQuitMessage(null);
        Player p = e.getPlayer();
        if(Core.hash.get(p.getUniqueId()) != null) {
            Bukkit.getScheduler().cancelTask(Core.hash.get(p.getUniqueId()));
        }
        if(MoveEvent.hash.get(p.getUniqueId()) != null) {
            ParkourHandler.getParkour(MoveEvent.hash.get(p.getUniqueId())).removeCheckPoint(p.getUniqueId());
            ParkourHandler.getParkour(MoveEvent.hash.get(p.getUniqueId())).setIngame(p.getUniqueId(), false);
            MoveEvent.hash.remove(p.getUniqueId());
        }
    }
}
