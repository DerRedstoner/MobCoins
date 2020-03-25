package de.derredstoner.mobcoins.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.derredstoner.mobcoins.utils.FormatUtil;

public class GUI {

	public static ItemStack createItem(Inventory inv, String material, short type, int amount, int invSlot, String name, List<String> loreString) {
		ItemStack item;
		List<String> lore = new ArrayList<String>();
		
		item = new ItemStack(Material.getMaterial(material), amount, type);
		
		ItemMeta meta = item.getItemMeta();
		if(!name.equals("")) {
			meta.setDisplayName(FormatUtil.format(name));
		}
		for(String s : loreString) {
			lore.add(FormatUtil.format(s));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(invSlot, item);
		
		return item;
	}
	
	public static ItemStack createItem(Inventory inv, String material, short type, int amount, int invSlot, String name) {
		ItemStack item;
		
		item = new ItemStack(Material.getMaterial(material), amount, type);
		
		ItemMeta meta = item.getItemMeta();
		if(!name.equals("")) {
			meta.setDisplayName(FormatUtil.format(name));
		}
		item.setItemMeta(meta);
		inv.setItem(invSlot, item);
		
		return item;
	}

	
}
