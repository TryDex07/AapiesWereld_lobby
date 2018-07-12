package nl.trydex07.lobby.commands;

import nl.trydex07.lobby.handlers.SelectorHandler;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class CMDHandler implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if(cmd.equalsIgnoreCase("lobby")) {
            if(!sender.hasPermission("lobby.admin")){
                sender.sendMessage(Utility.format(Utility.getMessage("NoPerm")));
            }else {
                Player p = (Player) sender;
                if (args.length == 0) {
                    sender.sendMessage(Utility.format("&7-----&8[&bLobby&8]&7-----"));
                    sender.sendMessage(Utility.format("&b/lobby additem &7<&bName&7> &7<&bServerName&7> &7<&bMaterial&7> &7<&bByte&7> &7<&bDisplayname&7> &7<&bslot&7> &7<&bClickable&7>"));
                    sender.sendMessage(Utility.format("&b/lobby addskull &7<&bName&7> &7<&bSkullOwner&7> &7<&bDisplayname&7> &7<&bServerName&7> &7<&bSlot&7> &7<&bClickable&7> "));
                    sender.sendMessage(Utility.format("&7-----&8[&bLobby&8]&7-----"));
                    return false;
                }
                if (args[0].equalsIgnoreCase("additem")) {
                    if (args.length < 6) {
                        sender.sendMessage(Utility.format("&b/lobby additem &7<&bName&7> &7<&bServerName&7> &7<&bMaterial&7> &7<&bByte&7> &7<&bDisplayname&7> &7<&bslot&7> &7<&bClickable&7>"));
                        return false;
                    }
                    ItemStack stack = new ItemStack(Material.getMaterial(args[3].toUpperCase()), 1, (short) Integer.parseInt(args[4]));
                    ItemMeta meta = stack.getItemMeta();
                    meta.setDisplayName(args[5]);
                    List<String> list = new ArrayList<>();
                    list.add("");
                    list.add("");
                    meta.setLore(list);
                    stack.setItemMeta(meta);
                    new SelectorHandler(args[1]).addItem(args[2], stack, Integer.parseInt(args[6]), Boolean.valueOf(args[7]));
                    sender.sendMessage(Utility.getMessage("Additem").replace("{player}", "" + p));
                }

                if (args[0].equalsIgnoreCase("addskull")) {
                    if (args.length < 6) {
                        sender.sendMessage(Utility.format("&b/lobby addskull &7<&bName&7> &7<&bSkullOwner&7> &7<&bDisplayname&7> &7<&bServerName&7> &7<&bSlot&7> &7<&bClickable&7> "));
                        return false;
                    }
                    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setOwner(args[1]);
                    skullMeta.setDisplayName(args[2]);
                    ArrayList<String> eenlore = new ArrayList<String>();
                    eenlore.add("");
                    eenlore.add("");
                    skullMeta.setLore(eenlore);
                    skull.setItemMeta(skullMeta);
                    new SelectorHandler(args[3]).addSkull(args[4], args[1], skull, Integer.parseInt(args[5]), Boolean.valueOf(args[6]));
                    sender.sendMessage(Utility.getMessage("Addskull").replace("{player}", "" + p));
                }
            }
        }

        return false;
    }
}
