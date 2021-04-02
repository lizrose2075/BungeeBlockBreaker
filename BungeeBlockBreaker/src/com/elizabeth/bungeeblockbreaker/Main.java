package com.elizabeth.bungeeblockbreaker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	
	@Override
	public void onEnable() {
		
		Main.instance = this;
		
		new Config(this);
		
		new Arena();
		
		getCommand("arena").setExecutor(new ArenaCommand());
		
		Bukkit.getPluginManager().registerEvents(new GameListener(), this);
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	public static Main getInstance() {return instance;}

}
