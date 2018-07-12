package nl.trydex07.lobby.handlers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class ItemHandler {

    private ItemStack stack;

    public void addItem(Player p, Material mat, int count,int b, String display, List<String> lore, int invplek){
        stack = new ItemStack(mat,count, (short) b);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(display);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        p.getInventory().setItem(invplek, stack);
    }

    public ItemStack getItem(Material mat, int count, int b, String display){
        stack = new ItemStack(mat,count, (short) b);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(display);
        stack.setItemMeta(meta);
        return stack;
    }

    public void addPlayerProfile(Player p, String display, List<String> lore, int invplek){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(p.getName());
        skullMeta.setDisplayName(display);
        skullMeta.setLore(lore);
        skull.setItemMeta(skullMeta);
        p.getInventory().setItem(invplek, skull);
    }


    public ItemStack addSKull(String skullowner, String display){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(skullowner);
        skullMeta.setDisplayName(display);
        skull.setItemMeta(skullMeta);
        return skull;
    }
}
