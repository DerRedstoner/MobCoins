package de.derredstoner.mobcoins.handlers;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.derredstoner.mobcoins.mysql.MySQLData;
import de.derredstoner.mobcoins.utils.FormatUtil;
import de.derredstoner.mobcoins.utils.MessageUtil;

public class BuyHandler {

	public static void onBuy(Player player, String material, short type, String name, int amount, int cost) {
		
		int balance = MySQLData.getInstance().getCoins(player.getName());
		
		if(balance < cost) {
			int diff = cost - balance;
			player.sendMessage(MessageUtil.boughtError(diff));
			return;
		}
		
		ItemStack item = new ItemStack(Material.getMaterial(material), amount, type);
		ItemMeta meta = item.getItemMeta();
		if(name != null) {
			meta.setDisplayName(FormatUtil.format(name));
		}
		item.setItemMeta(meta);
		
		if(hasSlot(player)) {
			player.getInventory().addItem(item);
			player.sendMessage(MessageUtil.boughtItem(material, amount, cost));
			player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
			MySQLData.getInstance().updateCoins(player.getName(), balance - cost);
		} else {
			player.sendMessage(MessageUtil.inventoryFull());
		}
		
	}
	
	public static boolean hasSlot(Player player){
	    Inventory inv = player.getInventory();
	    for (ItemStack item: inv.getContents()) {
	         if(item == null) {
	                 return true;
	         }
	     }
	    return false;
	}
	
}
