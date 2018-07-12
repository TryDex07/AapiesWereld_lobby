package nl.trydex07.lobby.mysql.basic;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.utilitys.FileManager;


public class Config {

    private static FileManager fm = new FileManager(Core.getPlugin(Core.class), "MySQL.yml");

    private static final String host = "host";
    private static final String user = "user";
    private static final String port = "port";
    private static final String password = "password";
    private static final String database = "database";

    public Config() {
    }

    public static void clear() {
        fm.getConfig().set("host", "");
        fm.getConfig().set("port", "3306");
        fm.getConfig().set("user", "");
        fm.getConfig().set("password", "");
        fm.getConfig().set("database", "");
        fm.saveConfig();
    }

    public static void create() {
        if(getHost() == null) {
            if(getDatabase() == null) {
                if(getPassword() == null) {
                    if(getUser() == null) {
                        fm.getConfig().set("host", "");
                        fm.getConfig().set("port", "3306");
                        fm.getConfig().set("user", "");
                        fm.getConfig().set("password", "");
                        fm.getConfig().set("database", "");
                        fm.saveConfig();
                    }
                }
            }
        }
    }


    public static String getHost() {
        return fm.getConfig().getString("host");
    }

    public static String getUser() {
        return fm.getConfig().getString("user");
    }

    public static String getPort() {
        return fm.getConfig().getString("port");
    }

    public static String getPassword() {
        return fm.getConfig().getString("password");
    }

    public static String getDatabase() {
        return fm.getConfig().getString("database");
    }

}
