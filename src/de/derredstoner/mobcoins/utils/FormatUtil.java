package de.derredstoner.mobcoins.utils;

import org.bukkit.ChatColor;

public class FormatUtil {

	public static String format(String text){
		if(text == null || text.equals("")) {
			return "";
		}
        return ChatColor.translateAlternateColorCodes('&', text);
    }
	
}
