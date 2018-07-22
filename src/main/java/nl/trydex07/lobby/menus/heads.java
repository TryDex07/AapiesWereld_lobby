package nl.trydex07.lobby.menus;

import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class heads {

    private Inventory inv;

    int[] bytes = {1,2,3,4,5,6,9,10,11,12,13,14};

    public void setup(Player p){
        inv = Bukkit.createInventory(null, 27, Utility.getMessage("Heads-Name"));
        ItemStack coal = new ItemHandler().getItem(Material.COAL_ORE, 1, 0, "§7Coal Ore");
        ItemStack iron = new ItemHandler().getItem(Material.IRON_ORE, 1, 0, "§fIron Ore");
        ItemStack gold = new ItemHandler().getItem(Material.GOLD_ORE, 1, 0, "§6Gold Ore");
        ItemStack lapis = new ItemHandler().getItem(Material.LAPIS_ORE, 1, 0, "§9Lapis Ore");
        ItemStack diamond = new ItemHandler().getItem(Material.DIAMOND_ORE, 1, 0, "§bDiamond Ore");
        ItemStack question = new ItemHandler().addSKull("MHF_question","§7Vraagteken");
        ItemStack vraagteken = new ItemHandler().addSKull("MHF_Exclamation","§7Uitroepteken");

        ItemStack Glas = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 1, "§6Gekleurd Glas");
        ItemStack Glas1 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 2, "§dGekleurd Glas");
        ItemStack Glas2 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 3, "§bGekleurd Glas");
        ItemStack Glas3 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 4, "§eGekleurd Glas");
        ItemStack Glas4 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 5, "§aGekleurd Glas");
        ItemStack Glas5 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 6, "§dGekleurd Glas");
        ItemStack Glas6 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 9, "§9Gekleurd Glas");
        ItemStack Glas7 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 10, "§dGekleurd Glas");
        ItemStack Glas8 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 11, "§5Gekleurd Glas");
        ItemStack Glas9 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 12, "§7Gekleurd Glas");
        ItemStack Glas10 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 13, "§2Gekleurd Glas");
        ItemStack Glas11 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 14, "§cGekleurd Glas");

        ItemStack tnt = new ItemHandler().getItem(Material.TNT, 1, 12, "§cTnT");
        ItemStack table = new ItemHandler().getItem(Material.ENCHANTMENT_TABLE, 1, 13, "§5Enchantment Table");
        ItemStack portal = new ItemHandler().getItem(Material.ENDER_PORTAL_FRAME, 1, 14, "§2End Portal");
        ItemStack enderchest = new ItemHandler().getItem(Material.ENDER_CHEST, 1, 14, "§5Enderchest");


        inv.setItem(0,coal);
        inv.setItem(1,iron);
        inv.setItem(2,gold);
        inv.setItem(3,lapis);
        inv.setItem(4,diamond);
        inv.setItem(5,question);
        inv.setItem(6,vraagteken);
        inv.setItem(7,Glas);
        inv.setItem(8,Glas1);
        inv.setItem(9,Glas2);
        inv.setItem(10,Glas3);
        inv.setItem(11,Glas4);
        inv.setItem(12,Glas5);
        inv.setItem(13,Glas6);
        inv.setItem(14,Glas7);
        inv.setItem(15,Glas8);
        inv.setItem(16,Glas9);
        inv.setItem(17,Glas10);
        inv.setItem(18,Glas11);
        inv.setItem(19,tnt);
        inv.setItem(20,table);
        inv.setItem(21,portal);
        inv.setItem(22,enderchest);


        p.openInventory(inv);

    }
}
