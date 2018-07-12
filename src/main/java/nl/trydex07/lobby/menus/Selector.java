package nl.trydex07.lobby.menus;


import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class Selector {

    private FileManager fm = new FileManager(Core.getPlugin(Core.class), "SelectorItems.yml");
    private FileManager sel1 = new FileManager(Core.getPlugin(Core.class), "SelectorSkulls.yml");

    private FileManager fm1 = new FileManager(Core.getPlugin(Core.class), "Config.yml");
    private List<String> list;
    private List<String> list1;
    private List<String> lore;
    private Inventory inv;

    public void open(Player p){
        inv = Bukkit.createInventory(null,fm1.getConfig().getInt("Selector-size"), Utility.format("&4Server Selector"));
        addToList();
        for(String names : list){
            ItemStack stack = new ItemStack(Material.STONE);
            stack.setTypeId(fm.getConfig().getInt("Selector." + names + ".item"));
            stack.setAmount(1);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(fm.getConfig().getString("Selector." + names + ".name"));
            meta.setLore(fm.getConfig().getStringList("Selector." + names + ".lore"));
            stack.setItemMeta(meta);
            inv.setItem(fm.getConfig().getInt("Selector." + names + ".slot"), stack);
        }
        addToList1();
        for(String names : list1){
            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            skullMeta.setOwner(sel1.getConfig().getString("skulls." + names + ".skullOwner"));
            skullMeta.setDisplayName(sel1.getConfig().getString("skulls." + names + ".name"));
            skullMeta.setLore(sel1.getConfig().getStringList("skulls." + names + ".lore"));
            skull.setItemMeta(skullMeta);
            inv.setItem(sel1.getConfig().getInt("skulls." + names + ".slot"), skull);
        }
        p.openInventory(inv);
    }

    public void addToList(){
        list = new ArrayList<>();
        for(String l : fm.getConfig().getConfigurationSection("Selector").getKeys(false)){
            list.add(l);
        }
    }
    public void addToList1(){
        list1 = new ArrayList<>();
        for(String l : sel1.getConfig().getConfigurationSection("skulls").getKeys(false)){
            list1.add(l);
        }
    }

}
