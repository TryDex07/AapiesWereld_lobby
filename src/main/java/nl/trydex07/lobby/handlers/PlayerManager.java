package nl.trydex07.lobby.handlers;

import nl.trydex07.lobby.abstracts.PlayerAbstract;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;
import java.util.UUID;

public class PlayerManager extends PlayerAbstract{

    public PlayerManager(String playerName, UUID uuid){
        setPlayerName(playerName);
        setUuid(uuid);
    }

    @Override
    public void execute(PlayerInteractEvent e) {

    }


}
