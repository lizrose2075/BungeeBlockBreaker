package com.elizabeth.bungeeblockbreaker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class Config {
	
	private static Main main;
	
	public Config(Main main) {
		Config.main = main;
		main.getConfig().options().copyDefaults();
		main.saveDefaultConfig();
	
	}
	
	public static int getRequiredPlayers() {return main.getConfig().getInt("required-players");}
	
	public static int getCountdownSeconds() {return main.getConfig().getInt("countdown-seconds");}

	public static Location getWaitingSpawn() {
		return new Location( 
				Bukkit.getWorld(main.getConfig().getString("waiting-spawn" + ".world")),
				main.getConfig().getDouble("waiting-spawn" + ".x"),
				main.getConfig().getDouble("waiting-spawn" +".y"),
				main.getConfig().getDouble("waiting-spawn" +".z"),
				main.getConfig().getInt("waiting-spawn" + ".yaw"),
				main.getConfig().getInt("waiting-spawn" +".pitch"));
	}
	
	public static Location getArenaSpawn() {
		
		String world = main.getConfig().getString("spawn.world");
		World serverWorld = Bukkit.createWorld(new WorldCreator(world));
		serverWorld.setAutoSave(false);
		
		
		return new Location( 
				serverWorld,
				main.getConfig().getDouble("spawn" + ".x"),
				main.getConfig().getDouble("spawn" +".y"),
				main.getConfig().getDouble("spawn" +".z"),
				main.getConfig().getInt("spawn" + ".yaw"),
				main.getConfig().getInt("spawn" +".pitch"));
	}
	
}
