package nl.trydex07.lobby.particles;


import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import nl.trydex07.lobby.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Helix {

    public void createHelix(final Player player) {
        Integer runnable =
            new BukkitRunnable() {
                double phi = 0;

                @Override
                public void run() {
                    if(player.isOnline() == false){
                        cancel();
                        return;
                    }
                    phi += Math.PI / 8;
                    double x, y, z;
                    Location loc = player.getLocation();
                    for (double t = 0; t <= 2 * Math.PI; t += Math.PI / 16) {
                        for (double i = 0; i <= 1; i += 1) {
                            x = 0.7 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi + i * Math.PI);
                            y = 0.03 * t;
                            z = 0.7 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi + i * Math.PI);
                            loc.add(0, 0.05, 0);
                            loc.add(x, y, z);

                            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FIREWORKS_SPARK, true, (float) (loc.getX()), (float) (loc.getY()), (float) (loc.getZ()), (float) 0, (float) 0, (float) 0, (float) 0, 1);
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                ((CraftPlayer) online).getHandle().playerConnection.sendPacket(packet);
                            }
                            loc.subtract(x, y, z);

                        }
                    }
                }
            }.runTaskTimer(Core.getPlugin(Core.class), 0, 5).getTaskId();
        Core.hash.put(player.getUniqueId(), runnable);
    }
}
