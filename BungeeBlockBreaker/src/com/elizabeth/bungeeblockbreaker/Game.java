package com.elizabeth.bungeeblockbreaker;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Game {
	
	private HashMap<UUID, Integer> points;
	
	public Game( ) {
		this.points = new HashMap<>();
	}
	
	public void start() {
		Arena.setState(GameState.LIVE);
		
		Arena.sendMessage(ChatColor.GREEN + "Game has started! Your objective is to be the first to break 20 blocks!");
	
		for(UUID uuid : Arena.getPlayers()) {
			points.put(uuid, 0);
			Bukkit.getPlayer(uuid).teleport(Config.getWaitingSpawn());
		}
	}
	
	public void addPoint(Player player) {
		int p = points.get(player.getUniqueId()) + 1;
		
		if(p == 20){
			Arena.sendMessage(ChatColor.GOLD + player.getName() + " WINS!!!");
			
			Arena.reset();
			return;
		}
		points.replace(player.getUniqueId(), p);
	}
}
