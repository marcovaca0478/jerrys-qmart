package com.jqmart.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jqmart.inventory.InventoryItem;

/**
 * Menu.  Used to build a list of Menu Options.
 * 
 * @author Marco Vaca
 */
public class Menu {

	// New method
	public static List<MenuOption> generateMenuFromInventory(Map<String, InventoryItem> items) {
		List<MenuOption> options = new ArrayList<MenuOption>();
		Set<String> keys = items.keySet();

		for (String currKey : keys) {
			MenuOption mo = new MenuOption(currKey, items.get(currKey).getName());
			options.add(mo);
		}

		return options;
	}
}
