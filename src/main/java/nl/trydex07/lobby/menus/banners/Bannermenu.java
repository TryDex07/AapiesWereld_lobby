package nl.trydex07.lobby.menus.banners;

import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class Bannermenu {

    private Inventory inv;

    public void setup(Player p){
        inv = Bukkit.createInventory(null, 27, Utility.format("&4Banners"));
        inv.setItem(0,Color.colorBanner(DyeColor.WHITE));
        inv.setItem(1,Color.colorBanner(DyeColor.RED));
        inv.setItem(2,Color.colorBanner(DyeColor.BLUE));
        inv.setItem(3,Color.colorBanner(DyeColor.GREEN));
        inv.setItem(4,Color.colorBanner(DyeColor.BROWN));
        inv.setItem(5,Color.colorBanner(DyeColor.CYAN));
        inv.setItem(6,Color.colorBanner(DyeColor.PINK));
        inv.setItem(7, Pokeball.pokeballBanner());

        p.openInventory(inv);
    }


}
