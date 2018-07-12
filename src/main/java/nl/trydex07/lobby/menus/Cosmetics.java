package nl.trydex07.lobby.menus;

import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Cosmetics {

    private Inventory inv;

    public void setup(Player p){
        inv = Bukkit.createInventory(null, 27, Utility.format("&4Cosmetics"));

        ItemStack stack = new ItemHandler().getItem(Material.REDSTONE_COMPARATOR,1 , 0, "Gadgets");
        ItemStack stack1 = new ItemHandler().getItem(Material.BANNER,1 , 0, "Banner");
        ItemStack stack2 = new ItemHandler().getItem(Material.HAY_BLOCK,1 , 0, "Heads");
        ItemStack stack3 = new ItemHandler().getItem(Material.BLAZE_POWDER,1 , 0, "Effecten");
        ItemStack stack4 = new ItemHandler().getItem(Material.RED_MUSHROOM,1 , 0, "Reset cosmetics");

        inv.setItem(4, stack);
        inv.setItem(11, stack1);
        inv.setItem(15, stack2);
        inv.setItem(20, stack3);
        inv.setItem(24, stack4);
        p.openInventory(inv);
    }





}
