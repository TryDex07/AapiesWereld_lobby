package nl.trydex07.lobby.mysql.database;

import org.bukkit.entity.Player;

import java.sql.*;

public class SQL {


    public SQL() {
    }

    public static boolean tableExists(String table) {
        if(table == null) {
            return false;
        } else {
            try {
                Connection connection = MySQL.getConnection();
                if(connection == null) {
                    return false;
                }

                DatabaseMetaData metadata = connection.getMetaData();
                if(metadata == null) {
                    return false;
                }

                ResultSet rs = metadata.getTables((String)null, (String)null, table, (String[])null);
                if(rs.next()) {
                    return true;
                }
            } catch (Exception var4) {
                ;
            }

            return false;
        }
    }

    public static void setString(Player p, String tabel, String what, String to) {
            MySQL.update("UPDATE `" + tabel + "` SET " + what + "='" + to + "' WHERE uuid='" + p.getUniqueId() + "'");
    }

    public static void setInt(Player p, String tabel, String what, int to) {
        MySQL.update("UPDATE `" + tabel + "` SET " + what + "='" + to + "' WHERE uuid='" + p.getUniqueId() + "'");
    }

    public static String getString(Player p, String select) {
        try{
            PreparedStatement statement = MySQL.con.prepareStatement("SELECT " + select + " FROM `Lobby` WHERE uuid=?;");
            statement.setString(1, p.getUniqueId().toString());

            ResultSet result = statement.executeQuery();
            result.next();

            return result.getString(select);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int getInt(Player p,String table, String select) {
        try{
            PreparedStatement statement = MySQL.con.prepareStatement("SELECT " + select + " FROM `"+table + "` WHERE uuid=?;");
            statement.setString(1, p.getUniqueId().toString());

            ResultSet result = statement.executeQuery();
            result.next();

            return result.getInt(select);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void insertData(String columns, String values, String table) {
        MySQL.update("INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")");
    }

    public static void deleteData(String column, String logic_gate, String data, String table) {
        if(data != null) {
            data = "'" + data + "'";
        }

        MySQL.update("DELETE FROM " + table + " WHERE " + column + logic_gate + data + ";");
    }

    public static boolean exists(String column, String data, String table) {
        if(data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + "=" + data);

            while(rs.next()) {
                if(rs.getString(column) != null) {
                    return true;
                }
            }
        } catch (Exception var4) {
            ;
        }

        return false;
    }

    public static void deleteTable(String table) {
        MySQL.update("DROP TABLE " + table + ";");
    }

    public static void truncateTable(String table) {
        MySQL.update("TRUNCATE TABLE " + table + ";");
    }

    public static void createTable(String table, String columns) {
        if(!tableExists(table)) {
            MySQL.update("CREATE TABLE " + table + " (" + columns + ")");
        }

    }

    public static void set(String selected, Object object, String column, String logic_gate, String data, String table) {
        if(object != null) {
            object = "'" + object + "'";
        }

        if(data != null) {
            data = "'" + data + "'";
        }

        MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + column + logic_gate + data + ";");
    }

    public static Object get(String selected, String column, String logic_gate, String data, String table) {
        if(data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + logic_gate + data);
            if(rs.next()) {
                return rs.getObject(selected);
            }
        } catch (Exception var6) {
        }

        return null;
    }

    public int countRows(String table) {
        int i = 0;
        if(table == null) {
            return i;
        } else {
            ResultSet rs = MySQL.query("SELECT * FROM " + table);

            try {
                while(rs.next()) {
                    ++i;
                }
            } catch (Exception var5) {
                ;
            }

            return i;
        }
    }
}
