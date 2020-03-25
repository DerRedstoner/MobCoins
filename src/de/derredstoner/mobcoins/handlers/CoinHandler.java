package de.derredstoner.mobcoins.handlers;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.derredstoner.mobcoins.main.Main;
import de.derredstoner.mobcoins.mysql.MySQLData;
import de.derredstoner.mobcoins.utils.MessageUtil;

public class CoinHandler {
	
	public static void addCoins(Player player, String entitytype) {
		
		if(!Main.getPlugin().getConfig().contains("coins."+entitytype)) {
			return;
		}
		int add = Main.getPlugin().getConfig().getInt("coins."+entitytype);
		player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
		
		if(add != 0) {
			int oldcoins = MySQLData.getInstance().getCoins(player.getName());
			MySQLData.getInstance().updateCoins(player.getName(), oldcoins + add);
			player.sendMessage(MessageUtil.onKill(entitytype.toLowerCase(), add));
		}
		
	}
	
}
