package nl.trydex07.lobby.listeners;

import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.menus.banners.Color;
import nl.trydex07.lobby.menus.banners.Pokeball;
import nl.trydex07.lobby.mysql.database.SQL;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class ParticleJoin implements Listener{

    ItemStack coal = new ItemHandler().getItem(Material.COAL_ORE, 1, 0, "Coal");
    ItemStack iron = new ItemHandler().getItem(Material.IRON_ORE, 1, 0, "Iron");
    ItemStack gold = new ItemHandler().getItem(Material.GOLD_ORE, 1, 0, "Gold");
    ItemStack lapis = new ItemHandler().getItem(Material.LAPIS_ORE, 1, 0, "Lapis");
    ItemStack diamond = new ItemHandler().getItem(Material.DIAMOND_ORE, 1, 0, "Diamond");
    ItemStack question = new ItemHandler().addSKull("MHF_question","Vraagtezken");
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

    @EventHandler
    public void on(PlayerJoinEvent e){
        Player p = e.getPlayer();
        String item = SQL.getString(e.getPlayer(), "head");

        if(item.equalsIgnoreCase("none")){
            return;
        }else if(item.equalsIgnoreCase("010"+ "0")){
            p.getInventory().setHelmet(coal);
        }else if(item.equalsIgnoreCase("010"+"1")){
            p.getInventory().setHelmet(iron);
        }else if(item.equalsIgnoreCase("010"+"2")){
            p.getInventory().setHelmet(gold);
        }else if(item.equalsIgnoreCase("010"+"3")){
            p.getInventory().setHelmet(lapis);
        }else if(item.equalsIgnoreCase("010"+"4")){
            p.getInventory().setHelmet(diamond);
        }else if(item.equalsIgnoreCase("010"+"5")){
            p.getInventory().setHelmet(question);
        }else if(item.equalsIgnoreCase("010"+"6")){
            p.getInventory().setHelmet(vraagteken);
        }else if(item.equalsIgnoreCase("010"+"7")){
            p.getInventory().setHelmet(glass);
        }else if(item.equalsIgnoreCase("010"+"8")){
            p.getInventory().setHelmet(glass1);
        }else if(item.equalsIgnoreCase("010"+"9")){
            p.getInventory().setHelmet(glass2);
        }else if(item.equalsIgnoreCase("010"+"10")){
            p.getInventory().setHelmet(glass3);
        }else if(item.equalsIgnoreCase("010"+"11")){
            p.getInventory().setHelmet(glass4);
        }else if(item.equalsIgnoreCase("010"+"12")){
            p.getInventory().setHelmet(glass5);
        }else if(item.equalsIgnoreCase("010"+"13")){
            p.getInventory().setHelmet(glass6);
        }else if(item.equalsIgnoreCase("010"+"14")){
            p.getInventory().setHelmet(glass7);
        }else if(item.equalsIgnoreCase("010"+"15")){
            p.getInventory().setHelmet(glass8);
        }else if(item.equalsIgnoreCase("010"+"16")){
            p.getInventory().setHelmet(glass9);
        }else if(item.equalsIgnoreCase("010"+"17")){
            p.getInventory().setHelmet(glass10);
        }else if(item.equalsIgnoreCase("010"+"18")){
            p.getInventory().setHelmet(glass11);
        }else if(item.equalsIgnoreCase("010"+"19")){
            p.getInventory().setHelmet(tnt);
        }else if(item.equalsIgnoreCase("010"+"20")){
            p.getInventory().setHelmet(table);
        }else if(item.equalsIgnoreCase("010"+"21")){
            p.getInventory().setHelmet(portal);
        }else if(item.equalsIgnoreCase("010"+"22")){
            p.getInventory().setHelmet(enderchest);
        }else if(item.equalsIgnoreCase("020"+"0")){
            p.getInventory().setHelmet(Color.colorBanner(DyeColor.WHITE));
        }else if(item.equalsIgnoreCase("020"+"1")){
            p.getInventory().setHelmet(Color.colorBanner(DyeColor.RED));
        }else if(item.equalsIgnoreCase("020"+"2")){
            p.getInventory().setHelmet(Color.colorBanner(DyeColor.BLUE));
        }else if(item.equalsIgnoreCase("020"+"3")){
            p.getInventory().setHelmet(Color.colorBanner(DyeColor.GREEN));
        }else if(item.equalsIgnoreCase("020"+"4")){
            p.getInventory().setHelmet(Color.colorBanner(DyeColor.BROWN));
        }else if(item.equalsIgnoreCase("020"+"5")){
            p.getInventory().setHelmet(Color.colorBanner(DyeColor.CYAN));
        }else if(item.equalsIgnoreCase("020"+"6")){
            p.getInventory().setHelmet(Color.colorBanner(DyeColor.PINK));
        }else if(item.equalsIgnoreCase("020"+"7")){
            p.getInventory().setHelmet(Pokeball.pokeballBanner());
        }
    }
}
