package nl.trydex07.lobby.abstracts;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class SelectorAbstract {

    private String name;
    private String servername;
    private ItemStack item;


    public abstract void execute(PlayerInteractEvent e);

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }
}
