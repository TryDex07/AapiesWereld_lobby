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
        inv = Bukkit.createInventory(null, 45, Utility.getMessage("Cosmetic-Name"));

        ItemStack stack = new ItemHandler().getItem(Material.REDSTONE_COMPARATOR,1 , 0, Utility.getCos("gadgets"));
        ItemStack stack1 = new ItemHandler().getItem(Material.BANNER,1 , 0, Utility.getCos("banners"));
        ItemStack stack2 = new ItemHandler().getItem(Material.HAY_BLOCK,1 , 0, Utility.getCos("heads"));
        ItemStack stack3 = new ItemHandler().getItem(Material.BLAZE_POWDER,1 , 0, Utility.getCos("effecten"));
        ItemStack stack4 = new ItemHandler().getItem(Material.RED_MUSHROOM,1 , 0, Utility.getCos("reset"));

        inv.setItem(34, stack);
        inv.setItem(11, stack1);
        inv.setItem(15, stack2);
        inv.setItem(28, stack3);
        inv.setItem(13, stack4);
        p.openInventory(inv);
    }





}
