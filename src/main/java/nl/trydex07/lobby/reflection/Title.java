package nl.trydex07.lobby.reflection;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Title {

    private ReflectionManager getReflection = new ReflectionManager();

    public void sendTitle(Player player, String title, String subtitle, Integer fadein, Integer stay, Integer fadeout) {

        try {
            if (title != null) {

                Object message = getReflection.getChatBaseComponent(title);
                Constructor<?> constructor = getReflection.getNMSClass("PacketPlayOutTitle").getConstructor(getReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getReflection.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
                Object packet = constructor.newInstance(getReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), message, fadein, stay, fadeout);
                getReflection.sendPacket(player, packet);
            }
            if (subtitle != null) {

                Object message = getReflection.getChatBaseComponent(subtitle);
                Constructor<?> constructor = getReflection.getNMSClass("PacketPlayOutTitle").getConstructor(getReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getReflection.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
                Object packet = constructor.newInstance(getReflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), message, fadein, stay, fadeout);
                getReflection.sendPacket(player, packet);
            }

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}
