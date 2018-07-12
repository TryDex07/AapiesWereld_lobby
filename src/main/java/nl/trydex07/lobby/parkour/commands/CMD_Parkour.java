package nl.trydex07.lobby.parkour.commands;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.handlers.ItemHandler;
import nl.trydex07.lobby.parkour.ParkourHandler;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CMD_Parkour implements CommandExecutor{

    public static String parkourname;
    private  FileManager fm = new FileManager(Core.getPlugin(Core.class), "CheckPoints.yml");
    private FileManager parkour = new FileManager(Core.getPlugin(Core.class), "Config.yml");


    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if(cmd.equalsIgnoreCase("parkour")){
            if(!sender.hasPermission("parkour.admin")){
                sender.sendMessage(Utility.format(Utility.getMessage("NoPerm")));
            }else {
                Player p = (Player) sender;
                if (args.length == 0) {
                    sender.sendMessage(Utility.format("&7-----&8[&bLobby&8]&7-----"));
                    sender.sendMessage(Utility.format("&b/parkour create &7<&bName&7> "));
                    sender.sendMessage(Utility.format("&b/parkour finish"));
                    sender.sendMessage(Utility.format("&b/parkour setcancelspawn"));
                    sender.sendMessage(Utility.format("&b/parkour remove &7<&bName&7>"));

                    sender.sendMessage(Utility.format("&7-----&8[&bLobby&8]&7-----"));
                    return false;
                }
                if (args[0].equalsIgnoreCase("create")) {
                    if (args.length < 2) {
                        sender.sendMessage(Utility.format("&b/parkour create &7<&bName&7> "));
                        return false;
                    }
                    ItemStack stack = new ItemHandler().getItem(Material.STICK, 1, 0, Utility.format("&bCheckpoint&7(Left click)"));
                    ItemMeta meta = stack.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    meta.addEnchant(Enchantment.DAMAGE_UNDEAD, 0, false);
                    stack.setItemMeta(meta);

                    if (stack != null) {
                        new ParkourHandler(args[1].toLowerCase());
                        parkourname = args[1].toLowerCase();
                        p.getInventory().addItem(stack);

                        ParkourHandler.init();
                        for (String name : fm.getConfig().getConfigurationSection("CheckPoints").getKeys(false)) {
                            new ParkourHandler(name);
                        }
                        p.sendMessage(Utility.format(Utility.getMessage("parkourcreate")));
                    }


                }
                if (args[0].equalsIgnoreCase("finish")) {
                    ParkourHandler.getParkour(parkourname).setUse(false);
                    ParkourHandler.getParkour(parkourname).save();
                    p.sendMessage(Utility.format(Utility.getMessage("parkourfinish")));
                }
                if (args[0].equalsIgnoreCase("setcancelspawn")) {
                    parkour.getConfig().set("parkourspawn", p.getLocation());
                    parkour.saveConfig();
                    p.sendMessage(Utility.format(Utility.getMessage("parkourspawn")));
                }
                if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length < 2) {
                        sender.sendMessage(Utility.format("&b/parkour remove &7<&bName&7> "));
                        return false;
                    }
                    ParkourHandler.getParkour(args[0].toLowerCase()).remove(args[0].toLowerCase());
                    sender.sendMessage(Utility.format(Utility.getMessage("parkourremove")));
                }
            }
        }
        return false;
    }
}
