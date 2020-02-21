package com.jqmart.inventory;

import java.util.Map;

/**
 * Strategy pattern to use for loading data into the Inventory
 * 
 * @author Marco Vaca
 */
public interface DataLoaderStrategy {
	Map<String, InventoryItem> populateInventory();
}
