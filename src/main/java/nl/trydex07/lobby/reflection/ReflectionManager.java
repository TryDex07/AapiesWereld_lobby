package nl.trydex07.lobby.reflection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionManager {

    protected String name;

    public ReflectionManager() {

    }

    public Class<?> getNMSClass(String name) {
        this.name = name;
        try {
            return Class.forName("net.minecraft.server." + getVersion() + "." + this.name);
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
        }
        return null;
    }

    public Class<?> getCraftBukkitClass(String name) {
        this.name = name;
        try {
            return Class.forName("org.bukkit.craftbukkit." + getVersion() + "." + this.name);
        } catch (ClassNotFoundException e) {

        }
        return null;
    }

    public Class<?> getBukkitClass(String nmsClassString) throws ClassNotFoundException {
        String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3]
                + ".";
        String name = "org.bukkit.craftbukkit." + version + nmsClassString;
        Class<?> nmsClass = Class.forName(name);
        return nmsClass;
    }

    public String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    public Object getConnection(Player player) {

        try {
            Method getHandle = player.getClass().getMethod("getHandle");
            Object nmsPlayer = getHandle.invoke(player);
            Field conField = nmsPlayer.getClass().getField("playerConnection");
            Object con = conField.get(nmsPlayer);
            return con;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendPacket(Player player, Object packet) {
        try {
            Object connection;
            connection = getConnection(player);
            Method getPacket = connection.getClass().getMethod("sendPacket", getNMSClass("Packet"));
            getPacket.invoke(connection, packet);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void setField(Object from, String name, Object to) {
        try {
            Field field;
            field = from.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(from, to);
            field.setAccessible(false);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }

    public Object getChatBaseComponent(String message) {
        try {
            return getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + message + "\"}");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        return null;
    }

}
