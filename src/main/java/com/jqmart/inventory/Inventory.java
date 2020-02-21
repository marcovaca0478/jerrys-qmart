package com.jqmart.inventory;

import java.util.Collection;
import java.util.Map;

/**
 * Inventory for Jerry's Qmart For this implementation, I don't need to
 * manipulate the Inventory beyond the initial setup via FILE.
 * 
 * Thus, the implementation is simply done via a Map
 * 
 * @author Marco Vaca
 */
public class Inventory {

	// Data Loader
	// private DataLoaderStrategy loader;

	// The actual "stock" for the Qmart
	private Map<String, InventoryItem> items;

	// Default constructor is marked as private to prevent use
	// Instead, we will populate it via the data loader Strategy
	private Inventory() {
	}

	/*
	 * Inventory generation via Strategy (data load) / public
	 * Inventory(DataLoaderStrategy loader) { Inventory inventory = new Inventory();
	 * inventory.loader = loader;
	 * 
	 * inventory = loader.populateInventory();
	 * 
	 * return inventory; }
	 */

	/*
	 * Inventory generation via Strategy (data load)
	 */
	public static Inventory generateInventory(DataLoaderStrategy loader) {
		Inventory inventory = new Inventory();
		inventory.items = loader.populateInventory();

		return inventory;
	}

	/*
	 * public void prueba() { items.stream().filter(item ->
	 * "01".equals(item.getProduct().getBarCode())).findAny().orElse(null); }
	 */

	/*
	 * Used to count the number of DIFFERENT items available on stock
	 * 
	 * Not to be confused with the TOTAL quantity for each item
	 */
	public int numberOfInventoryItems() {
		return this.items == null ? 0 : this.items.size();
	}

	/*
	 * Used to count the total quantity of ALL inventory items available on stock
	 */
	public int totalQuantityOfInventoryItems() {
		if (null == this.items)
			return 0;

		int totalQuantity = 0;

		Collection<InventoryItem> values = items.values();

		for (InventoryItem inventoryItem : values) {
			totalQuantity += inventoryItem.getQuantity();
		}
		return totalQuantity;
	}

}
