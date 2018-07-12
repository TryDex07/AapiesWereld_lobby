package nl.trydex07.lobby.utilitys;

import java.util.ArrayList;
import java.util.List;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import nl.trydex07.lobby.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class Utility {

    public static Core core = Core.getPlugin(Core.class);
    private static FileManager fm = new FileManager(Core.getPlugin(Core.class), "Messages.yml");

    public static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static List<String> format(List<String> list) {
        List<String> x = new ArrayList<>();
        for (String str : list) {
            x.add(str.replace("&", "§"));
        }
        return x;
    }

    public static String getMessage(String message){

        fm.reloadConfig();
        if(fm.getConfig().getConfigurationSection( "Berichten" ).contains(message)){
            String s = String.valueOf(fm.getConfig().getConfigurationSection( "Berichten" ).get(message));
            return s.replaceAll("&", "§");

        }else{
            String wrong1 = "§cHet bericht " + message + " §ckan niet geladen worden! Contacteer een developer!";
            return wrong1;
        }
    }

    public static void send(Player p, String server) {
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server);
            p.sendMessage("Je wordt verzonden naar de server §4" + server + "§7!");
            p.sendPluginMessage(Core.getPlugin(Core.class), "BungeeCord", out.toByteArray());
        } catch (Exception e) {

            p.sendMessage("§cEr ging iets fout! Meld dit aan een Developer: " + e.getMessage());
        }
    }


}


