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

public class Pokeball {

    public static ItemStack pokeballBanner()
    {
        ItemStack i = new ItemStack(Material.BANNER, 1);
        BannerMeta m = (BannerMeta) i.getItemMeta();

        m.setBaseColor(DyeColor.WHITE);

        List<Pattern> patterns = new ArrayList<Pattern>();

        patterns.add(new Pattern(DyeColor.RED, PatternType.HALF_HORIZONTAL));
        patterns.add(new Pattern(DyeColor.BLACK, PatternType.RHOMBUS_MIDDLE));
        patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_TOP));
        patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_BOTTOM));
        patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
        patterns.add(new Pattern(DyeColor.WHITE, PatternType.CIRCLE_MIDDLE));
        m.setPatterns(patterns);
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.setDisplayName(ChatColor.ITALIC + "" + ChatColor.GREEN + "Awesome banner!"); //Setting the display name to "Awesome banner!"
        m.setLore(Arrays.asList(ChatColor.DARK_AQUA + "Awesome banner lore!"));//Setting the lore to "Awesome banner lore!"

        i.setItemMeta(m);
        return i;
    }


}
