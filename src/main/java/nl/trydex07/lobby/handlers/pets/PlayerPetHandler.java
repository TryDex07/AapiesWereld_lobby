package nl.trydex07.lobby.handlers.pets;

import com.google.common.collect.Maps;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import nl.trydex07.lobby.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class PlayerPetHandler {

    private static Map<UUID, PlayerPetHandler> petHandlerMap = Maps.newHashMap();

    public static PlayerPetHandler getUserPet(UUID uuid) {
        if (petHandlerMap.get(uuid) != null) {
            return petHandlerMap.get(uuid);
        } else {
            return null;
        }
    }

    private UUID uuid;
    private EntityType type;
    private String name;
    private LivingEntity entity;

    public PlayerPetHandler(UUID uuid, EntityType type, String name) {
        this.uuid = uuid;
        this.type = type;
        this.name = name;

        Player owner = Bukkit.getPlayer(uuid);
        entity = (LivingEntity) owner.getWorld().spawnEntity(owner.getLocation(), type);
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
        entity.setNoDamageTicks(1000000000);
    }

    public void tick() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player owner = Bukkit.getPlayer(uuid);
                double distance = entity.getLocation().distanceSquared(owner.getLocation());
                if (distance > 10) {
                    if (distance > 510 && owner.isOnGround()) {
                        entity.teleport(owner);
                    }
                    walkTo(owner.getLocation().clone(), 1.4);
                }
            }

        }.runTaskTimer(Core.getPlugin(Core.class), 10, 10);
    }

    public void walkTo(Location target, double speed) {
        EntityInsentient craftLivingEntity = (EntityInsentient) ((CraftLivingEntity) entity).getHandle();
        craftLivingEntity.getNavigation().a(target.getX(), target.getY(), target.getBlockZ(), speed);
    }
}
