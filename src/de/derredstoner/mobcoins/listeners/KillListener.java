package de.derredstoner.mobcoins.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import de.derredstoner.mobcoins.handlers.CoinHandler;

public class KillListener implements Listener {

	@EventHandler(priority=EventPriority.MONITOR)
	public void onKill(EntityDeathEvent event) {
		
		if(event.getEntity().getKiller() == null) {
			return;
		}
		
		Entity entity = event.getEntity();
		Player player = event.getEntity().getKiller();
		String entitytype = entity.getType().toString();
		
		CoinHandler.addCoins(player, entitytype);
		
	}
	
}
