package de.derredstoner.mobcoins.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.derredstoner.mobcoins.mysql.MySQLData;

public class JoinListener implements Listener {

	@EventHandler(priority=EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		MySQLData.getInstance().createPlayer(player.getName());
	}
	
}
