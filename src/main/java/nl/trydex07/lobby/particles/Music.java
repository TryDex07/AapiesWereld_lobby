package nl.trydex07.lobby.particles;

import nl.trydex07.lobby.Core;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Music {


    int ParticleZ = 1;
    int ParticleStage = 0;
    double ParticleX = -1;
    double ParticleY = -1;
    boolean goBack = false;

    public void sendParticle(Player p, Effect effect){
        Integer runnable =
            new BukkitRunnable() {
                public void run() {
                    if(p.isOnline() == false){
                        cancel();
                        return;
                    }
                    ParticleX = Math.sin((double) ParticleStage / 3) / 1.5;
                    ParticleY = Math.cos((double) ParticleStage / 3) / 1.5;
                    ParticleStage++;
                    if (goBack) {
                        if (ParticleZ < 0) goBack = false;
                        ParticleZ--;
                    } else {
                        if (ParticleZ > 50) goBack = true;
                        ParticleZ++;
                    }
                    Location loc = p.getLocation().add(ParticleX, (double) ParticleZ / 30, ParticleY);
                    Bukkit.getWorld(p.getLocation().getWorld().getName()).playEffect(loc, effect, 1);
                }

            }.runTaskTimer(Core.getPlugin(Core.class), 0, 1).getTaskId();
        Core.hash.put(p.getUniqueId(), runnable);
    }
}
