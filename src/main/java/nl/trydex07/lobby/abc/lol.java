package nl.trydex07.lobby.abc;

import com.google.common.collect.Sets;
import nl.trydex07.lobby.handlers.PlayerManager;
import nl.trydex07.lobby.mysql.database.SQL;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class lol {

    private Map<String, PlayerManager> manager =  new HashMap<>();
    private Set<UUID> uuids;
    private String name;
    Player p;

    public lol(String playerName, Player p){
        uuids = Sets.newHashSet();
        manager.put(playerName, new PlayerManager(playerName, p.getUniqueId()));
        uuids.add(p.getUniqueId());
        name = playerName;
    }

    public void setFly(String b){
        SQL.setString(p, "lobby","fly", b);
        getManByName(name).setFly(b);
    }

    public String getFly(){
        String s = SQL.getString(p, "fly");
        getManByName(name).setFly(s);
        return getManByName(name).getFly();
    }

    public void setToglePlayers(String b){
        SQL.setString(p, "lobby","toggleplayer", b);
        getManByName(name).setFly(b);
    }

    public String getToglePlayers(){
        String s = SQL.getString(p, "toggleplayer");
        getManByName(name).setTogglePlayerName(s);
        return getManByName(name).getFly();
    }


    public  PlayerManager getManByName(String name){
        return manager.get(name);
    }

    public Map<String, PlayerManager> getManager() {
        return manager;
    }
}
