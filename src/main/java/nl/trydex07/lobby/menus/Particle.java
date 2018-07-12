package nl.trydex07.lobby.menus;

import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Particle {

    private Inventory inv;

    public void setup(Player p) {
        inv = Bukkit.createInventory(null, 27, Utility.format("&4Particle"));
        ItemStack stack = new ItemHandler().getItem(Material.ENCHANTED_BOOK, 1, 0, Utility.format("&ETOL"));
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
        stack.setItemMeta(meta);

        ItemStack stack1 = new ItemHandler().getItem(Material.FIREWORK, 1, 0, Utility.format("&ESharp Boom"));
        ItemStack stack2 = new ItemHandler().getItem(Material.INK_SACK, 1, 1, Utility.format("&EHartjes"));
        ItemStack stack3 = new ItemHandler().getItem(Material.DOUBLE_PLANT, 1, 0, Utility.format("&EMuziek"));


        inv.setItem(10, stack);
        inv.setItem(12, stack1);
        inv.setItem(14, stack2);
        inv.setItem(16, stack3);
        p.openInventory(inv);

    }
}
