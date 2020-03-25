package de.derredstoner.mobcoins.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.derredstoner.mobcoins.main.Main;
import de.derredstoner.mobcoins.mysql.MySQLData;
import de.derredstoner.mobcoins.utils.FormatUtil;

public class Shop {
	
	public static int rows = Main.getPlugin().getConfig().getInt("shop.rows");

	public static void openShop(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, (rows+1)*9, "§2MobCoins Shop");
		
		for(int i = 0; i < rows*9; i++) {
			
			if(Main.getPlugin().getConfig().contains("shop."+i)) {
				
				String material = Main.getPlugin().getConfig().getString("shop."+i+".item");
				int type = Main.getPlugin().getConfig().getInt("shop."+i+".type");
				int amount = Main.getPlugin().getConfig().getInt("shop."+i+".amount");
				int slot = Main.getPlugin().getConfig().getInt("shop."+i+".slot");
				int cost = Main.getPlugin().getConfig().getInt("shop."+i+".cost");
				String name = Main.getPlugin().getConfig().getString("shop."+i+".name");
				String[] lore = Main.getPlugin().getConfig().getString("shop."+i+".lore").split(",");
				List<String> loreList = new ArrayList<>();
				if(lore.length != 0) {
					for(int x = 0; x < lore.length; x++) {
						loreList.add(lore[x]);
					}
				}
				loreList.add("");
				loreList.add(FormatUtil.format("&6Price: §e"+cost+" mobcoins"));
				
				GUI.createItem(inv, material, (short) type, amount, slot, name, loreList);
			}
			
		}
		
		List<String> balancelore = new ArrayList<>();
		balancelore.add("&e"+MySQLData.getInstance().getCoins(player.getName())+" MobCoins");
		GUI.createItem(inv, "YELLOW_FLOWER", (short) 0, 1, (rows*9)+4, "&6Your balance:", balancelore);
		
		GUI.createItem(inv, "ARROW", (short) 0, 1, (rows*9), "&cClose menu");
		
		player.openInventory(inv);
		
	}
	
	public static void closeShop(Player player) {
		player.closeInventory();
	}
	
}
