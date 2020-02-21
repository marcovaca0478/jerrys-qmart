package com.jqmart.inventory;

import java.util.Map;

/**
 * Data loader used for tests. Loads NO Products
 * 
 * @author Marco Vaca
 */
public class MockEmptyDataLoader implements DataLoaderStrategy {

	public Map<String, InventoryItem> populateInventory() {

		// An empty map
		// Map<String, InventoryItem> m = new HashMap<String, InventoryItem>();

		// return m;

		// A null map
		return null;
	}
}
