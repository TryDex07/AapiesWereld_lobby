package nl.trydex07.lobby.abstracts;

import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;
import java.util.UUID;

public abstract class PlayerAbstract {

    private UUID uuid;
    private String playerName;
    private String fly;
    private String togglePlayerName;

    public abstract void execute(PlayerInteractEvent e);

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getFly() {
        return fly;
    }

    public void setFly(String fly) {
        this.fly = fly;
    }

    public String getTogglePlayerName() {
        return togglePlayerName;
    }

    public void setTogglePlayerName(String togglePlayerName) {
        this.togglePlayerName = togglePlayerName;
    }
}
