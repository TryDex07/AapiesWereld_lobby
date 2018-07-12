package nl.trydex07.lobby.mysql.database;

import nl.trydex07.lobby.mysql.basic.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {

    public static Connection con;

    public MySQL() {
    }

    public static Connection getConnection() {
        return con;
    }

    public static void setConnection(String host, String port, String user, String password, String database) {
        if(host != null && user != null && password != null && database != null) {
            disconnect(false);

            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
            } catch (Exception var5) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Connect Error: " + var5.getMessage());
            }

        }
    }

    public static void connect() {
        connect(true);
    }

    private static void connect(boolean message) {
        String host = Config.getHost();
        String user = Config.getUser();
        String port = Config.getPort();
        String password = Config.getPassword();
        String database = Config.getDatabase();
        if(isConnected()) {
            if(message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Connect Error: Er is al een SQL verbinding");
            }
        } else if(host.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Host is niet ingevuld");
        } else if(user.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: User is niet ingevuld");
        } else if(password.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Password is niet ingevuld");
        } else if(database.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Database is niet ingevuld");
        } else if(port.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Port is niet ingevuld");
        } else {
            setConnection(host, port, user, password, database);
        }

    }

    public static void disconnect() {
        disconnect(true);
    }

    private static void disconnect(boolean message) {
        try {
            if(isConnected()) {
                con.close();
            } else if(message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Disconnect Error: No existing connection");
            }
        } catch (Exception var2) {
            if(message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Disconnect Error: " + var2.getMessage());
            }
        }

        con = null;
    }

    public static void reconnect() {
        disconnect();
        connect();
    }

    public static boolean isConnected() {
        return getConnection() != null;
    }

    public static void update(String command) {
        if(command != null) {
            connect(false);

            try {
                Statement st = getConnection().createStatement();
                st.executeUpdate(command);
                st.close();
            } catch (Exception var2) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Update:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + var2.getMessage());
            }

        }
    }

    public static ResultSet query(String command) {
        if(command == null) {
            return null;
        } else {
            connect(false);
            ResultSet rs = null;

            try {
                Statement st = getConnection().createStatement();
                rs = st.executeQuery(command);
            } catch (Exception var3) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Query:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + var3.getMessage());
            }

            return rs;
        }
    }
}
