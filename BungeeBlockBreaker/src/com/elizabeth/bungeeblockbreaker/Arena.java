package com.elizabeth.bungeeblockbreaker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Arena {
	
	private static ArrayList<UUID> players;
	private static Location spawn;
	private static GameState state;
	private static Countdown countdown;
	private static Game game;
	
	public Arena() {
		players = new ArrayList<>();
		spawn = Config.getArenaSpawn();
		state = GameState.RECRUITING;
		countdown = new Countdown();
		game = new Game();
		
	}
	
	public static void start() {
		game.start();
	}
	
	public static void reset() {
		for(UUID uuid : players) {
			Bukkit.getPlayer(uuid).teleport(Config.getWaitingSpawn());
		}
		
		state = GameState.RECRUITING;
		countdown = new Countdown();
		game = new Game();
		
		if(players.size() == Config.getRequiredPlayers()) {
			countdown.begin();
		}
		
	}
	
	public static void sendMessage(String message) {
		for(UUID uuid : players) {
			Bukkit.getPlayer(uuid).sendMessage(message);
		}
	}
	
	public static void addPlayer(Player player) {
		players.add(player.getUniqueId());
		
		if(players.size() == Config.getRequiredPlayers()) {
			countdown.begin();
		}
	}
	
	public static void removePlayer(Player player) {
		players.remove(player.getUniqueId());
		
		player.getInventory().clear();
		
		if(players.size() <= Config.getRequiredPlayers()) {
			if(state.equals(GameState.COUNTDOWN)) {
				reset();
				return;
			}
		}
		
		if(players.size() == 0 && state.equals(GameState.LIVE)) {
			reset();
			return;
		}
		
	}
	public static List<UUID> getPlayers() {return players;}
	public static GameState getState() {return state;}
	public static void setState(GameState state) {Arena.state = state;}
	public static Game getGame() {return game;}
	public static World getWorld() {return spawn.getWorld();}
}
