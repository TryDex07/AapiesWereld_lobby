package nl.trydex07.lobby.handlers;

import nl.trydex07.lobby.abstracts.SelectorAbstract;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectorManager extends SelectorAbstract{

    public SelectorManager(String name){
        setName(name);
    }

    @Override
    public void execute(PlayerInteractEvent e) {

    }
}
