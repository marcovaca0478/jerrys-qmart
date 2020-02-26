package com.jqmart.inventory;

import java.util.Collection;
import java.util.Map;

import com.jqmart.product.Product;

/**
 * Inventory for Jerry's Qmart For this implementation, I don't need to
 * manipulate the Inventory beyond the initial setup via FILE.
 * 
 * Thus, the implementation is simply done via a Map
 * 
 * @author Marco Vaca
 */
public class Inventory {

	// The actual "stock" for the Qmart
	private Map<String, InventoryItem> items;

	// Default constructor is marked as private to prevent use
	// Instead, we will populate it via the data loader Strategy
	private Inventory() {
	}

	// Inventory generation via Strategy (data load)
	public static Inventory generateInventory(DataLoaderStrategy loader) {
		Inventory inventory = new Inventory();
		inventory.items = loader.populateInventory();

		return inventory;
	}

	// Inventory generation via Strategy (data load). Also takes a pathAndFilename
	public static Inventory generateInventory(DataLoaderStrategy loader, String pathAndFilename) {
		Inventory inventory = new Inventory();
		inventory.items = loader.populateInventory(pathAndFilename);

		return inventory;
	}

	/*
	 * Used to count the number of DIFFERENT items available on stock
	 * 
	 * Not to be confused with the TOTAL quantity for each item
	 */
	public int numberOfInventoryItems() {
		return this.items == null ? 0 : this.items.size();
	}

	// Used to count the total quantity of ALL inventory items available on stock
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

	// Gets the stock for a given barcode
	public int getStock(String barCode) {
		InventoryItem item = this.items.get(barCode);
		return item.getQuantity();
	}

	// Increases the stock for a given item
	public void increaseItemStock(String barCode, int quantityToIncrease) {
		InventoryItem item = this.items.get(barCode);
		item.increaseQuantity(quantityToIncrease);

		items.replace(barCode, item);
	}

	// Decreases the stock for a given item
	public boolean decreaseItemStock(String barCode, int quantityToDecrease) {
		InventoryItem item = this.items.get(barCode);

		// Impossible to decrease Stock if not enough items
		if (quantityToDecrease > item.getQuantity()) {
			// System.out.println("Not enough stock! No changes were made.");
			return false;
		}

		item.decreaseQuantity(quantityToDecrease);
		items.replace(barCode, item);

		return true;
	}

	// Gets a Product, given the Bar Code
	public Product getProduct(String barCode) {
		InventoryItem item = this.items.get(barCode);

		if (null == item)
			return null;
		else
			return item.getProduct();
	}

	// ---- UNTESTED

	public Map<String, InventoryItem> getItems() {
		return this.items;
	}

}
