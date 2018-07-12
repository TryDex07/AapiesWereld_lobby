package nl.trydex07.lobby.API;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.handlers.PlayerHandler;
import nl.trydex07.lobby.utilitys.FileManager;
import org.bukkit.entity.Player;

public class API {

    private static FileManager fm = new FileManager(Core.getPlugin(Core.class), "Config.yml");

    public static void setPlayerFly(Player p, Boolean b){
        new PlayerHandler(p.getName(), p).setFly(Boolean.toString(b));
    }

    public static String getPlayerFly(Player p){
        return new PlayerHandler(p.getName(), p).getFly();
    }

    public static void setPlayerVisability(Player p, Boolean b){
        new PlayerHandler(p.getName(), p).setToglePlayers(Boolean.toString(b));
    }

    public static String setPlayerVisability(Player p){
        return new PlayerHandler(p.getName(), p).getToglePlayers();
    }

    public static int getJoinedPlayers(){
        return fm.getConfig().getInt("joined");
    }
}
