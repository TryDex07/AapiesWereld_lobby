package nl.trydex07.lobby.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.handlers.crate.CrateHandler;
import nl.trydex07.lobby.handlers.crate.RandomHandler;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class InteractCrateEvent implements Listener {

	List<String> list;
	public static HashMap<String, Player> cratenaam = new HashMap<>();
	private HashMap<String, String> inUse = new HashMap<>();
	private HashMap<Player, Player> Players = new HashMap<>();
	String world;
	double x, y, z;
	FileManager fm = new FileManager(Core.getPlugin(Core.class), "Crates.yml");

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			fm.reloadConfig();
			registerCates();
			for (String crate : list) {
				world = fm.getConfig().getString("Crates." + crate + ".world");
				x = fm.getConfig().getDouble("Crates." + crate + ".x");
				y = fm.getConfig().getDouble("Crates." + crate + ".y");
				z = fm.getConfig().getDouble("Crates." + crate + ".z");

				Location loc = new Location(Bukkit.getWorld(world), x, y, z);
				Location click = e.getClickedBlock().getLocation();
				if (loc.getX() == click.getX()) {
					if (loc.getY() == click.getY()) {
						if (loc.getZ() == click.getZ()) {
							e.setCancelled(true);
							if (new CrateHandler().getCrate(e.getPlayer())) {

								if (inUse.containsKey(crate)) {
									e.getPlayer().sendMessage(Utility.format("&8[&eCrate&8] &6Hij word momenteel al gebruikt."));
								} else {
									if (Players.containsKey(e.getPlayer())) {
										e.getPlayer().sendMessage(Utility.format("&8[&eCrate&8] &6je bent al een key aan het openen."));
									} else {
										inUse.put(crate, crate);
										cratenaam.put(crate, e.getPlayer());

										new CrateHandler().removeKey(e.getPlayer());
										new CrateHandler().invisableCrateArmorStand();
										new CrateHandler().moveArmorStand(e.getPlayer(), loc);

										new BukkitRunnable() {

											@Override
											public void run() {
												new CrateHandler().visableCrateArmorStand();
												new RandomHandler().setup(e.getPlayer());

												cratenaam.remove(crate);
												inUse.clear();
												for (Map.Entry<Player, Player> s : Players.entrySet()) {
												Players.remove(s.getKey());
												}
											}
										}.runTaskLater(Core.getPlugin(Core.class), 30);

										Players.put(e.getPlayer(), e.getPlayer());
									}
								}

							} else {
								e.getPlayer()
										.sendMessage(Utility.format("&8[&eCrate&8] &6Je hebt momenteel geen key's."));
							}

						}

					}
				}
			}
		}

	}

	public void registerCates() {
		list = new ArrayList<>();
		for (String section : fm.getConfig().getConfigurationSection("Crates").getKeys(false)) {
			list.add(section);
		}
	}

}
