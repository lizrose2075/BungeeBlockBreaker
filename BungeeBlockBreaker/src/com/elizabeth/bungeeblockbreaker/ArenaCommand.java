package com.elizabeth.bungeeblockbreaker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

;

public class ArenaCommand implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			 if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {

				player.sendMessage(ChatColor.GREEN + "You left the arena!");
				
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF("Lobby");
				
				player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
				
			}else {
				player.sendMessage(ChatColor.RED + "Invalid usage - these are the options: ");
				player.sendMessage(ChatColor.GOLD + "- /arena leave");
			}
		}else {
			System.out.println("You can't use this from the console");
		}
		
		return false;
	}

}
