package nl.trydex07.lobby.handlers.crate;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.utilitys.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class RandomHandler {

	private FileManager fm = new FileManager(Core.getPlugin(Core.class), "CrateItems.yml");
	public static HashMap<Player, ItemStack> SpawnedItems = new HashMap<>();
	private int index;
	private List<String> Items;

	public RandomHandler() {
	}

	public void setup(Player p) {
		Items = fm.getConfig().getStringList("Items");
		index = new Random().nextInt(Items.size());

		String items = Items.get(index);
		Bukkit.dispatchCommand(p, items.replace("%player%", p.getName()));
	}
}