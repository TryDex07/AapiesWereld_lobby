package nl.trydex07.lobby.handlers.crate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.listeners.InteractCrateEvent;
import nl.trydex07.lobby.mysql.database.SQL;
import nl.trydex07.lobby.reflection.FireworkManager;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;


public class CrateHandler {

	private static HashMap<String, CrateHandler> hash = new HashMap<>();

	public static CrateHandler getCrate(String name) {
		if (name != null) {
			return hash.get(name);
		}
		return null;
	}

	public static String getCrates() {
		for (Entry<String, CrateHandler> s : hash.entrySet()) {
			return s.getKey();
		}
		return null;
	}

	public CrateHandler() {

	}

	private FileManager fm = new FileManager(Core.getPlugin(Core.class), "Crates.yml");
	private Player p;
	private String name;
	private Color[] c = { Color.OLIVE, Color.ORANGE, Color.YELLOW, Color.FUCHSIA, Color.OLIVE, Color.ORANGE,
			Color.YELLOW, Color.FUCHSIA, Color.OLIVE, Color.ORANGE, Color.YELLOW, Color.FUCHSIA };

	public void init() {
		for (String config : fm.getConfig().getConfigurationSection("Crates").getKeys(false)) {
			hash.put(config, this);
		}
	}

	@SuppressWarnings("deprecation")
	public void create(Player p, String name) {
		this.p = p;
		this.name = name;
		Block loc = this.p.getTargetBlock((HashSet<Byte>) null, 10);
		if (loc.getType() != Material.ENDER_CHEST) {
			loc.setType(Material.ENDER_CHEST);
		}
		fm.getConfig().set("Crates." + this.name + ".world", loc.getWorld().getName());
		fm.getConfig().set("Crates." + this.name + ".x", loc.getX());
		fm.getConfig().set("Crates." + this.name + ".y", loc.getY());
		fm.getConfig().set("Crates." + this.name + ".z", loc.getZ());
		fm.saveConfig();

		ArmorStandHandler fm = new ArmorStandHandler();
		fm.create(loc.getLocation().add(0.500, 0.2, 0.500), Utility.format("&cBananenKrat"), true, false, null);
		fm.save(this.name, "1");
		ArmorStandHandler fm1 = new ArmorStandHandler();
		fm1.create(loc.getLocation().add(0.500, -0.1, 0.500), Utility.format("&7(Right Click)"), true, false, null);
		fm1.save(this.name, "2");
		hash.put(name, this);

	}

	public void remove(String name) {
		fm.getConfig().set("Crates." + name, null);
		fm.saveConfig();
		hash.clear();
		init();
		for (ArmorStand s : ArmorStandHandler.getArmorStands()) {
			s.remove();
		}
		new ArmorStandHandler().init();
	}

	public void invisableCrateArmorStand() {
		for (String s : InteractCrateEvent.cratenaam.keySet()) {
			fm.getConfig().set("Crates." + s + ".holoData.1.nameVisible", false);
			fm.getConfig().set("Crates." + s + ".holoData.2.nameVisible", false);

			fm.saveConfig();
			for (ArmorStand am : ArmorStandHandler.getArmorStands()) {
				am.remove();
			}
			new ArmorStandHandler().init();
		}

	}

	public void visableCrateArmorStand() {
		for (Map.Entry<String, Player> s : InteractCrateEvent.cratenaam.entrySet()) {
			if (InteractCrateEvent.cratenaam.size() != 0) {
				fm.getConfig().set("Crates." + s.getKey() + ".holoData.1.nameVisible", true);
				fm.getConfig().set("Crates." + s.getKey() + ".holoData.2.nameVisible", true);
				fm.saveConfig();

				InteractCrateEvent.cratenaam.remove(s.getKey());
				for (ArmorStand am : ArmorStandHandler.getArmorStands()) {
					am.remove();
				}
				new ArmorStandHandler().init();
			}
		}
	}

	public boolean getCrate(Player p) {
		if (SQL.getInt(p,"Crates", "BananenKey") == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void removeKey(Player p) {
		int newCrates = getPlayerKeys(p) - 1;
		SQL.setInt(p, "Crates", "BananenKey", newCrates);
	}
	
	public void addKey(Player p,int key) {
		int i = getPlayerKeys(p) + key;
		SQL.setInt(p, "Crates", "BananenKey", i);
	}

		public int getPlayerKeys(Player p) {
			return SQL.getInt(p,"Crates", "BananenKey");
	}

	public void moveArmorStand(Player p,Location loc) {
		Snowball a = (Snowball) loc.getWorld().spawnEntity(loc.add(0.500, 10, 0.500), EntityType.SNOWBALL);
		new BukkitRunnable() {
			Integer time = 25;
			@Override
			public void run() {
				if(time == 0) {
					a.remove();
					p.playSound(loc, Sound.EXPLODE, 200, 1);
					cancel();
				}
				FireworkManager fm = new FireworkManager();
				fm.createEffect(FireworkEffect.builder().flicker(false).withColor(c).build(), a.getLocation());
				time--;

			}
		}.runTaskTimer(Core.getPlugin(Core.class), 1, 1);
	}

}
