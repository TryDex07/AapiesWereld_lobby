package nl.trydex07.lobby.parkour;

import com.google.common.collect.Lists;
import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.utilitys.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.*;

public class ParkourHandler {

    private static HashMap<String, ParkourHandler> hash = new HashMap<>();
    private static List<String> names;

    public static ParkourHandler getParkour(String name){
        return hash.get(name);
    }

    public static List<String> getNames() {
        return names;
    }

    private HashMap<UUID, Location> checkpoint = new HashMap<>();
    private HashMap<UUID, Boolean> ingame = new HashMap<>();
    private FileManager fm = new FileManager(Core.getPlugin(Core.class), "CheckPoints.yml");
    private List<Location> Checkpoints;
    private String parkour;
    private boolean use = false;
    private Location start, finish;

    public ParkourHandler(String name){
        use = true;
        parkour = name;
        Checkpoints = new ArrayList<>();
        load();
        hash.put(name, this);
    }

    public void addCheckPoint(Location location){
        Checkpoints.add(location);
    }

    public static void init(){
         FileManager fm = new FileManager(Core.getPlugin(Core.class), "CheckPoints.yml");
         names = new ArrayList<>();
        for (String list : fm.getConfig().getConfigurationSection("CheckPoints").getKeys(false)) {
            names.add(list);
        }
    }

    public void load(){
        if(fm.getConfig().get("CheckPoints." + parkour) != null) {
            List<String> serialized = (List<String>) fm.getConfig().get("CheckPoints." + parkour + ".checkpoints");
            String start = String.valueOf(fm.getConfig().get("CheckPoints." + parkour + ".start"));
            String finish = String.valueOf(fm.getConfig().get("CheckPoints." + parkour + ".finish"));

            for (String str : serialized) {
                String[] args = str.split("::");
                int x, y, z;
                World world = Bukkit.getWorld(args[3]);
                x = Integer.parseInt(args[0]);
                y = Integer.parseInt(args[1]);
                z = Integer.parseInt(args[2]);
                Checkpoints.add(new Location(world, x, y, z));
            }
            String[] args = start.split("::");
            int x, y, z;
            World world = Bukkit.getWorld(args[3]);
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            z = Integer.parseInt(args[2]);
            this.start = new Location(world, x, y, z);

            int x1, y1, z1;
            String[] args1 = finish.split("::");
            World world1 = Bukkit.getWorld(args1[3]);
            x1 = Integer.parseInt(args1[0]);
            y1 = Integer.parseInt(args1[1]);
            z1 = Integer.parseInt(args1[2]);
            this.finish = new Location(world1, x1, y1, z1);
        }
    }

    public void save(){
        if(use == false) {
            List<String> list = Lists.newArrayList();

            for(Location loc : Checkpoints) {
                String serialized = loc.getBlockX() + "::" + loc.getBlockY() + "::" + loc.getBlockZ() + "::" + loc.getWorld().getName();
                list.add(serialized);
            }

            fm.getConfig().set("CheckPoints." + parkour + ".start", list.get(0));
            fm.getConfig().set("CheckPoints." + parkour + ".finish", list.get(list.size() -1));
            list.remove(list.size()-1);
            list.remove(0);
            fm.getConfig().set("CheckPoints." + parkour + ".checkpoints", list);
            fm.saveConfig();
        }
        fm.reloadConfig();
    }

    public void remove(String name){
        fm.getConfig().set("CheckPoints." + name, null);
        fm.saveConfig();
    }

    public void setCheckpoint(UUID uuid, Location loc){
        checkpoint.put(uuid, loc);
    }

    public Location getCheckPoint(UUID uuid){
        return checkpoint.get(uuid);
    }

    public Boolean getIngame(UUID uuid) {
        return ingame.get(uuid);
    }

    public boolean isInGame(UUID uuid){
        if(ingame.get(uuid) != null) {
            if (ingame.get(uuid) == true) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }
    public void setIngame(UUID uuid, boolean b){
        ingame.put(uuid, b);
    }

    public void removeCheckPoint(UUID uuid){
        if(checkpoint.get(uuid) != null) {
            checkpoint.remove(uuid);
        }
    }

    public List<Location> getCheckpoints() {
        return Checkpoints;
    }

    public void setCheckpoints(List<Location> checkpoints) {
        Checkpoints = checkpoints;
    }

    public String getParkour() {
        return parkour;
    }

    public void setParkour(String parkour) {
        this.parkour = parkour;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getFinish() {
        return finish;
    }

    public void setFinish(Location finish) {
        this.finish = finish;
    }
}
