package com.elizabeth.bungeeblockbreaker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class GameListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Arena.addPlayer(e.getPlayer());
		e.getPlayer().teleport(Config.getWaitingSpawn());
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		
		if(Arena.getState().equals(GameState.LIVE)) {
			player.sendMessage(ChatColor.GOLD + "+1 Point");
			
			Arena.getGame().addPoint(player);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Player player = e.getPlayer();
		Arena.removePlayer(player);
	}
	
}
