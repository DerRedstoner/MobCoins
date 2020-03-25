package de.derredstoner.mobcoins.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.derredstoner.mobcoins.commands.MobCoinsCommand;
import de.derredstoner.mobcoins.listeners.ClickListener;
import de.derredstoner.mobcoins.listeners.JoinListener;
import de.derredstoner.mobcoins.listeners.KillListener;
import de.derredstoner.mobcoins.mysql.MySQL;

public class Main extends JavaPlugin {

	private static Main Instance;
	
	public void onEnable() {
		Instance = this;
		
		createConfig();
		MySQL.getInstance().mysqlSetup();
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinListener(), this);
		pluginManager.registerEvents(new KillListener(), this);
		pluginManager.registerEvents(new ClickListener(), this);
		
		this.getCommand("mobcoins").setExecutor(new MobCoinsCommand());
		
		getLogger().info("MobCoins by DerRedstoner has been enabled.");
		
	}
	
	public void onDisable() {
		getLogger().info("MobCoins by DerRedstoner has been disabled.");
	}
	
	public static Main getPlugin() {
		return Instance;
	}
	
	//Define the config
	private FileConfiguration config;
	private static File configf;
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	
	//Create the default config file in folder
	public void createConfig() {

        configf = new File(this.getDataFolder(), "config.yml");

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            this.saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configf);
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(configf);
		try {
            config.load(configf);
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
