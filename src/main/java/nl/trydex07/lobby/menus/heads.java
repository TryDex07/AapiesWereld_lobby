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
        inv = Bukkit.createInventory(null, 27, Utility.format("&4Heads"));
        ItemStack coal = new ItemHandler().getItem(Material.COAL_ORE, 1, 0, "Coal");
        ItemStack iron = new ItemHandler().getItem(Material.IRON_ORE, 1, 0, "Iron");
        ItemStack gold = new ItemHandler().getItem(Material.GOLD_ORE, 1, 0, "Gold");
        ItemStack lapis = new ItemHandler().getItem(Material.LAPIS_ORE, 1, 0, "Lapis");
        ItemStack diamond = new ItemHandler().getItem(Material.DIAMOND_ORE, 1, 0, "Diamond");
        ItemStack question = new ItemHandler().addSKull("MHF_question","Vraagteken");
        ItemStack vraagteken = new ItemHandler().addSKull("MHF_Exclamation","Uitroepteken");

        ItemStack glass = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 1, "Glass");
        ItemStack glass1 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 2, "Glass");
        ItemStack glass2 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 3, "Glass");
        ItemStack glass3 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 4, "Glass");
        ItemStack glass4 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 5, "Glass");
        ItemStack glass5 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 6, "Glass");
        ItemStack glass6 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 9, "Glass");
        ItemStack glass7 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 10, "Glass");
        ItemStack glass8 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 11, "Glass");
        ItemStack glass9 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 12, "Glass");
        ItemStack glass10 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 13, "Glass");
        ItemStack glass11 = new ItemHandler().getItem(Material.STAINED_GLASS, 1, 14, "Glass");

        ItemStack tnt = new ItemHandler().getItem(Material.TNT, 1, 12, "Tnt");
        ItemStack table = new ItemHandler().getItem(Material.ENCHANTMENT_TABLE, 1, 13, "Enchantment_Table");
        ItemStack portal = new ItemHandler().getItem(Material.ENDER_PORTAL_FRAME, 1, 14, "End_portal_frame");
        ItemStack enderchest = new ItemHandler().getItem(Material.ENDER_CHEST, 1, 14, "Enderchest");


        inv.setItem(0,coal);
        inv.setItem(1,iron);
        inv.setItem(2,gold);
        inv.setItem(3,lapis);
        inv.setItem(4,diamond);
        inv.setItem(5,question);
        inv.setItem(6,vraagteken);
        inv.setItem(7,glass);
        inv.setItem(8,glass1);
        inv.setItem(9,glass2);
        inv.setItem(10,glass3);
        inv.setItem(11,glass4);
        inv.setItem(12,glass5);
        inv.setItem(13,glass6);
        inv.setItem(14,glass7);
        inv.setItem(15,glass8);
        inv.setItem(16,glass9);
        inv.setItem(17,glass10);
        inv.setItem(18,glass11);
        inv.setItem(19,tnt);
        inv.setItem(20,table);
        inv.setItem(21,portal);
        inv.setItem(22,enderchest);


        p.openInventory(inv);

    }
}
