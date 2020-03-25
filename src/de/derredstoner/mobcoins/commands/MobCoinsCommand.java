package de.derredstoner.mobcoins.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.derredstoner.mobcoins.gui.Shop;
import de.derredstoner.mobcoins.mysql.MySQLData;
import de.derredstoner.mobcoins.utils.MessageUtil;

public class MobCoinsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!command.getName().equalsIgnoreCase("mobcoins")) {
			return false;
		}
		if(sender instanceof Player == false) {
			return false;
		}
		
		Player player = (Player) sender;

		if(player.isOp()) {
			if(args.length == 0) {
				Shop.openShop(player);
			}
			else if(args.length == 1 && args[0].equalsIgnoreCase("help")) {
				player.sendMessage("§e§l/mobcoins §7Open the shop");
				player.sendMessage("§e§l/mobcoins <player> <coins> §7Set a players coins");
			}
			else if(args.length == 2) {
				MySQLData.getInstance().updateCoins(Bukkit.getPlayer(args[0]).getName(), Integer.valueOf(args[1]));
				player.sendMessage(MessageUtil.prefix()+" §aUpdated §f"+args[0]+"'s §abalance to §e"+args[1]+" mobcoins§a!");
			}
		}
		else {
			if(args.length == 0) {
				Shop.openShop(player);
			}
			else {
				player.sendMessage(MessageUtil.prefix()+" §cType §e/mobcoins §cto open the shop!");
			}
		}
		
		return false;
	}
	
}
