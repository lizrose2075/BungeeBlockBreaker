package com.elizabeth.bungeeblockbreaker;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;


public class Countdown extends BukkitRunnable {
	
	private static int seconds;
	
	public Countdown() {
		Countdown.seconds = Config.getCountdownSeconds();
	}
	
	public void begin() {
		Arena.setState(GameState.COUNTDOWN);
		this.runTaskTimer(Main.getInstance(), 0, 20l);

	}
	
	@Override
	public void run() {
		if(seconds == 0) {
			cancel();
			Arena.start();
			return;
		}
		
		if(seconds % 30 == 0|| seconds <= 10) {
			if(seconds == 1) {
				Arena.sendMessage(ChatColor.AQUA + "The game will begin in 1 second...");
			}else {
				Arena.sendMessage(ChatColor.AQUA + "The game will begin in " + seconds + "seconds...");
			}
		}
		
		if(Arena.getPlayers().size() < Config.getRequiredPlayers()) {
			cancel();
			Arena.setState(GameState.RECRUITING);
			Arena.sendMessage(ChatColor.RED + "The game cannot begin until there are " + Config.getRequiredPlayers() + " players, resetting countdown...");
			return;
		}
		
		seconds --;
	}
}
