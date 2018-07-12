package nl.trydex07.lobby.handlers.crate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.trydex07.lobby.Core;
import nl.trydex07.lobby.utilitys.FileManager;
import nl.trydex07.lobby.utilitys.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;


public class ArmorStandHandler {

	private static HashMap<ArmorStand, ArmorStandHandler> armorstand = new HashMap<>();

	public static List<ArmorStand> getArmorStands() {
		List<ArmorStand> a = new ArrayList<>();
		for (Map.Entry<ArmorStand, ArmorStandHandler> entry : armorstand.entrySet()) {
			a.add(entry.getKey());
		}
		return a;
	}

	private FileManager fm = new FileManager(Core.getPlugin(Core.class), "Crates.yml");

	private ArmorStand stand;
	private Location loc;

	public ArmorStandHandler() {

	}

	public void create(Location loc, String name,boolean nameVis, boolean helmet, Material mat) {
		this.loc = loc;
		stand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setGravity(false);
		stand.setVisible(false);
		stand.setCustomNameVisible(nameVis);
		stand.setCustomName(name);
		stand.setSmall(true);
		if (helmet) {
			stand.setHelmet(new ItemStack(mat));
		}
		armorstand.put(stand, this);
	}

	public void init() {
			for (String name : fm.getConfig().getConfigurationSection("Crates").getKeys(false)) {
				if(name != null){
				for (String holo : fm.getConfig().getConfigurationSection("Crates." + name + ".holoData").getKeys(false)) {
					if (holo != null) {
						String world = fm.getConfig().getString("Crates." + name + ".holoData." + holo + ".world");
						double x = fm.getConfig().getDouble("Crates." + name + ".holoData." + holo + ".x");
						double y = fm.getConfig().getDouble("Crates." + name + ".holoData." + holo + ".y");
						double z = fm.getConfig().getDouble("Crates." + name + ".holoData." + holo + ".z");

						Location loc = new Location(Bukkit.getWorld(world), x, y, z);
						if (loc != null) {
							ArmorStand s = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
							s.setGravity(false);
							s.setCustomName(Utility.format(fm.getConfig().getString("Crates." + name + ".holoData." + holo + ".name")));
							s.setCustomNameVisible(fm.getConfig().getBoolean("Crates." + name + ".holoData." + holo + ".nameVisible"));
							s.setVisible(fm.getConfig().getBoolean("Crates." + name + ".holoData." + holo + ".visible"));
							s.setSmall(true);
							armorstand.put(s, this);
						}
					}
				}
			}
		}
	}

	public void save(String name, String hv) {
		fm.getConfig().set("Crates." + name + ".holoData." + hv + ".world", stand.getLocation().getWorld().getName());
		fm.getConfig().set("Crates." + name + ".holoData." + hv + ".x", stand.getLocation().getX());
		fm.getConfig().set("Crates." + name + ".holoData." + hv + ".y", stand.getLocation().getY());
		fm.getConfig().set("Crates." + name + ".holoData." + hv + ".z", stand.getLocation().getZ());

		fm.getConfig().set("Crates." + name + ".holoData." + hv + ".name", stand.getName());
		fm.getConfig().set("Crates." + name + ".holoData." + hv + ".visible", stand.isVisible());
		fm.getConfig().set("Crates." + name + ".holoData." + hv + ".nameVisible", stand.isCustomNameVisible());
		fm.saveConfig();
	}

	public ArmorStand setNameVisible() {
		stand.setCustomNameVisible(true);
		return stand;
	}

	public ArmorStand getArmorStand() {
		return stand;
	}

	public Location getLocation() {
		return loc;
	}

	public ArmorStand remove() {
		stand.remove();
		return stand;
	}

}
