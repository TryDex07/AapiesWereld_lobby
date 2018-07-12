package nl.trydex07.lobby.menus.banners;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Color {

    public static ItemStack colorBanner(DyeColor color)
    {
        ItemStack i = new ItemStack(Material.BANNER, 1);
        BannerMeta m = (BannerMeta) i.getItemMeta();

        m.setBaseColor(color);
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.setDisplayName("colored"); //Setting the display name to "Awesome banner!"

        i.setItemMeta(m);
        return i;
    }

}
