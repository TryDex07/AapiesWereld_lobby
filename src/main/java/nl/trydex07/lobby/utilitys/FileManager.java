package nl.trydex07.lobby.utilitys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileManager {

	public static HashMap<String, FileManager> files = new HashMap<>();

	private JavaPlugin core;
	private String FileName;
	private File DataFolder;
	private File File;
	private YamlConfiguration FileConfiguration;

	public FileManager(JavaPlugin plugin, String filename) {
		if (plugin == null) {
			throw new IllegalArgumentException("plugin cannot be null");
		}
		filename = filename.endsWith(".yml") ? filename : filename + ".yml";
		this.core = plugin;
		this.FileName = filename;
		DataFolder = plugin.getDataFolder();
		if (DataFolder == null) {
			throw new IllegalStateException("DataFolder cannot be null");
		}

		File f = new File(DataFolder.toString() + File.separatorChar + filename);
		if (f != null) {
			this.File = new File(f.toString());
		}

		saveDefaultConfig();
		files.put(filename, this);
	}

	public void reloadConfig() {
		try {
			this.FileConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(this.File), "UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream defConfigStream = this.core.getResource(this.FileName);
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			this.FileConfiguration.setDefaults(defConfig);
		}
	}

	public FileConfiguration getConfig() {
		if (this.FileConfiguration == null) {
			reloadConfig();
		}
		return this.FileConfiguration;
	}

	public void saveConfig() {
		if ((this.FileConfiguration == null) || (this.File == null)) {
			return;
		}
		try {
			getConfig().save(this.File);
		} catch (IOException ex) {
			this.core.getLogger().log(Level.WARNING, "Could not save config to " + this.File, ex);
		}
	}

	public void saveDefaultConfig() {
		if (!this.File.exists()) {
			this.core.saveResource(this.FileName, false);
		}
	}

	
}
