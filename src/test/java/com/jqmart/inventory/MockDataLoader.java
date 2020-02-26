package com.jqmart.inventory;

import java.util.HashMap;
import java.util.Map;

import com.jqmart.product.Product;

/**
 * Data loader used for tests. Loads the three Products listed on example
 * 
 * @author Marco Vaca
 */
public class MockDataLoader implements DataLoaderStrategy {

	public Map<String, InventoryItem> populateInventory(String notUsed) {
		return populateInventory();
	}
	
	public Map<String, InventoryItem> populateInventory() {

		Map<String, InventoryItem> m = new HashMap<String, InventoryItem>();

		// 1st product
		Product p = new Product();
		p.setBarCode("D01");
		p.setName("Milk");
		p.setPrice(3.75);
		p.setMemberPrice(3.5);
		p.setTaxable(false);

		InventoryItem item = new InventoryItem(p, 50);
		// item.setProduct(p);
		// item.setQuantity(50);

		m.put(p.getBarCode(), item);

		// 2nd product
		p = new Product();
		p.setBarCode("D20");
		p.setName("Red Bull");
		p.setPrice(4.3);
		p.setMemberPrice(4.0);
		p.setTaxable(true);

		item = new InventoryItem(p, 50);

		m.put(p.getBarCode(), item);

		// 2nd product
		p = new Product();
		p.setBarCode("G06");
		p.setName("Flour");
		p.setPrice(3.1);
		p.setMemberPrice(2.75);
		p.setTaxable(false);

		item = new InventoryItem(p, 50);

		m.put(p.getBarCode(), item);

		// System.out.println("Size of inventory: " + m.size());

		return m;
	}

	/*
	 * public static void main(String[] args) { MockDataLoader idf = new
	 * MockDataLoader(); idf.populateInventory(); }
	 */
}
