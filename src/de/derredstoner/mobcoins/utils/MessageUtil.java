package de.derredstoner.mobcoins.utils;

import de.derredstoner.mobcoins.main.Main;

public class MessageUtil {

	public static String prefix() {
		return FormatUtil.format(Main.getPlugin().getConfig().getString("messages.prefix"));
	}
	
	public static String boughtItem(String item, int amount, int cost) {
		return FormatUtil.format(Main.getPlugin().getConfig().getString("messages.boughtSuccess")
				.replace("%prefix%", prefix()).replace("%amount%", ""+amount)
				.replace("%item%", item.toLowerCase()).replace("%cost%", ""+cost));
	}
	
	public static String yourBalance(int balance) {
		return FormatUtil.format(Main.getPlugin().getConfig().getString("messages.balance")
				.replace("%prefix%", prefix()).replace("%balance%", ""+balance));
	}
	
	public static String boughtError(int missing) {
		return FormatUtil.format(Main.getPlugin().getConfig().getString("messages.boughtError")
				.replace("%prefix%", prefix()).replace("%missing%", ""+missing));
	}
	
	public static String onKill(String entitytype, int coins) {
		if(coins == 1) {
			return FormatUtil.format(Main.getPlugin().getConfig().getString("messages.onReceiveOne")
					.replace("%prefix%", prefix()).replace("%coins%", ""+coins)
					.replace("%entity%", entitytype));
		} else {
			return FormatUtil.format(Main.getPlugin().getConfig().getString("messages.onReceive")
					.replace("%prefix%", prefix()).replace("%coins%", ""+coins)
					.replace("%entity%", entitytype));
		}
	}
	
	public static String shopTitle() {
		return FormatUtil.format(Main.getPlugin().getConfig().getString("messages.shopTitle"));
	}
	
	public static String inventoryFull() {
		return FormatUtil.format(Main.getPlugin().getConfig().getString("messages.inventoryFull")
				.replace("%prefix%", prefix()));
	}
	
}
