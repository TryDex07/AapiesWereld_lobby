package nl.trydex07.lobby.handlers;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.utilitys.FileManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectorHandler {

    private FileManager sel = new FileManager(Core.getPlugin(Core.class), "SelectorItems.yml");
    private FileManager sel1 = new FileManager(Core.getPlugin(Core.class), "SelectorSkulls.yml");

    private Map<String, SelectorManager> manager = new HashMap<>();
    private String name;
    private String skullOwner;
    private int slot;
    private List<String> list;
    private boolean clickable = true;

    public SelectorHandler(String name){
        manager.put(name, new SelectorManager(name));
        this.name = name;
    }

    public void addItem(String servername, ItemStack stack, int slot, boolean click) {
        this.clickable = click;

            getManByName(this.name).setItem(stack);
            getManByName(this.name).setServername(servername);
            this.slot = slot - 1;
            saveItem();
    }

    public void addSkull(String servername, String skullowner, ItemStack stack, int slot, boolean click) {
        this.clickable = click;
            this.skullOwner = skullowner;

        getManByName(this.name).setItem(stack);
        getManByName(this.name).setServername(servername);
        this.slot = slot - 1;
        saveSkull();
    }

    public void saveItem(){
        System.out.println(4);
        if(clickable == true) {
            System.out.println(5);
            ItemMeta meta = getManByName(this.name).getItem().getItemMeta();
            addToList();
            sel.getConfig().set("Selector." + name + ".item", getManByName(this.name).getItem().getTypeId());
            sel.getConfig().set("Selector." + name + ".amount", getManByName(this.name).getItem().getAmount());
            sel.getConfig().set("Selector." + name + ".name", meta.getDisplayName());
            sel.getConfig().set("Selector." + name + ".enchantments", meta.getEnchants());
            sel.getConfig().set("Selector." + name + ".slot", this.slot);
            sel.getConfig().set("Selector." + name + ".clickable", this.clickable);
            sel.getConfig().set("Selector." + name + ".serverName", getManByName(this.name).getServername());
            for (String list : list) {
                sel.getConfig().set("Selector." + name + ".lore", list);
            }
            sel.saveConfig();

        }else{
            ItemMeta meta = getManByName(this.name).getItem().getItemMeta();
            addToList();
            sel.getConfig().set("Selector." + name + ".item", getManByName(this.name).getItem().getTypeId());
            sel.getConfig().set("Selector." + name + ".amount", getManByName(this.name).getItem().getAmount());
            sel.getConfig().set("Selector." + name + ".name", meta.getDisplayName());
            sel.getConfig().set("Selector." + name + ".enchantments", meta.getEnchants());
            sel.getConfig().set("Selector." + name + ".slot", this.slot);
            sel.getConfig().set("Selector." + name + ".clickable", this.clickable);
            for (String list : list) {
                sel.getConfig().set("Selector." + name + ".lore", list);
            }
            sel.saveConfig();

        }
    }

    public void saveSkull(){
        System.out.println(4);
        if(clickable == true) {
            System.out.println(5);
            ItemMeta meta = getManByName(this.name).getItem().getItemMeta();
            addToList();
            sel1.getConfig().set("skulls." + name + ".amount", getManByName(this.name).getItem().getAmount());
            sel1.getConfig().set("skulls." + name + ".name", meta.getDisplayName());
            sel1.getConfig().set("skulls." + name + ".enchantments", meta.getEnchants());
            sel1.getConfig().set("skulls." + name + ".slot", this.slot);
            sel1.getConfig().set("skulls." + name + ".clickable", this.clickable);
            sel1.getConfig().set("skulls." + name + ".serverName", getManByName(this.name).getServername());
            sel1.getConfig().set("skulls." + name + ".skullOwner", skullOwner);

            for (String list : list) {
                sel1.getConfig().set("skulls." + name + ".lore", list);
            }
            sel1.saveConfig();

        }else{
            ItemMeta meta = getManByName(this.name).getItem().getItemMeta();
            addToList();
            sel1.getConfig().set("skulls." + name + ".amount", getManByName(this.name).getItem().getAmount());
            sel1.getConfig().set("skulls." + name + ".name", meta.getDisplayName());
            sel1.getConfig().set("skulls." + name + ".enchantments", meta.getEnchants());
            sel1.getConfig().set("skulls." + name + ".slot", this.slot);
            sel1.getConfig().set("skulls." + name + ".clickable", this.clickable);
            sel1.getConfig().set("skulls." + name + ".skullOwner", skullOwner);
            for (String list : list) {
                sel1.getConfig().set("skulls." + name + ".lore", list);
            }
            sel1.saveConfig();

        }
    }

    private void addToList(){
        ItemMeta meta = getManByName(this.name).getItem().getItemMeta();
        list = new ArrayList<>();
        for(String lores : meta.getLore()){
            list.add(lores);
        }
    }

    public  SelectorManager getManByName(String name){
        return manager.get(name);
    }

    public Map<String, SelectorManager> getManager() {
        return manager;
    }
}
