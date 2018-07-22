package nl.trydex07.lobby.parkour.listeners;

import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.parkour.ParkourHandler;
import nl.trydex07.lobby.parkour.commands.CMD_Parkour;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BreakEvent implements Listener{

    @EventHandler
    public void on(BlockBreakEvent e){
        if(e.getPlayer().getItemInHand() == null) {
            return;
        }
        if(e.getPlayer().getItemInHand() == null){
            return;
        }
        if(e.getPlayer().getItemInHand().getType() == Material.AIR){
            return;
        }

        ItemStack stack = new ItemHandler().getItem(Material.STICK, 1, 0, Utility.format("&6Checkpoint&7(Left click)"));
        ItemMeta meta = stack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.DAMAGE_UNDEAD, 0, false);
        stack.setItemMeta(meta);

        Player p = e.getPlayer();
        if(p.getItemInHand().getItemMeta().getDisplayName() != null){
            if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Utility.format("&6Checkpoint&7(Left click)"))) {
                e.setCancelled(true);
                ParkourHandler.getParkour(CMD_Parkour.parkourname).addCheckPoint(e.getBlock().getLocation());
            }
        }
    }
}
