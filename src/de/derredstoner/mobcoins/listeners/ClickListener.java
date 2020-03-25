package de.derredstoner.mobcoins.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.derredstoner.mobcoins.gui.Shop;
import de.derredstoner.mobcoins.handlers.BuyHandler;
import de.derredstoner.mobcoins.main.Main;
import de.derredstoner.mobcoins.mysql.MySQLData;
import de.derredstoner.mobcoins.utils.FormatUtil;
import de.derredstoner.mobcoins.utils.MessageUtil;

public class ClickListener implements Listener {
	
	public static int rows = Main.getPlugin().getConfig().getInt("shop.rows");

	@EventHandler(priority=EventPriority.LOWEST)
	public void onClick(InventoryClickEvent event) {
		String title = event.getInventory().getTitle();
		Player player = (Player) event.getWhoClicked();
		if(event.getCurrentItem() == null) {
			return;
		}
		if(title.contains("MobCoins") && event.getCurrentItem().getType() != Material.AIR) {
			
			event.setCancelled(true);
			
			String name = event.getCurrentItem().getItemMeta().getDisplayName();
			
			if(name != null && name.contains("Close menu")) {
				Shop.closeShop(player);
				return;
			}
			if(name != null && name.contains("Your balance")) {
				player.sendMessage(MessageUtil.yourBalance(MySQLData.getInstance().getCoins(player.getName())));
				Shop.closeShop(player);
				return;
			}
			
			int cost = -1;
			int amount = -1;
			String material = "";
			short type = 0;
			
			for(int i = 0; i < rows*9; i++) {
				if(Main.getPlugin().getConfig().contains("shop."+i)) {
					if(FormatUtil.format(Main.getPlugin().getConfig().getString("shop."+i+".name")).equals(FormatUtil.format(name))
							&& event.getCurrentItem().getType().toString().equals(Main.getPlugin().getConfig().getString("shop."+i+".item"))) {
						cost = Main.getPlugin().getConfig().getInt("shop."+i+".cost");
						amount = Main.getPlugin().getConfig().getInt("shop."+i+".amount");
						material = Main.getPlugin().getConfig().getString("shop."+i+".item");
						type = (short) Main.getPlugin().getConfig().getInt("shop."+i+".type");
						break;
					}
				}
			}
			
			if(cost < 0) {
				return;
			}
			
			BuyHandler.onBuy(player, material, type, name, amount, cost);
			
			Shop.closeShop(player);
		}
		
	}
	
}
