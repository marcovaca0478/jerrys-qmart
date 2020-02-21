package com.jqmart.inventory;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests for Inventory.
 * 
 * @author Marco Vaca
 */
public class InventoryTest {
	private static DataLoaderStrategy loader;
	private static Inventory inventory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loader = new MockDataLoader();
		inventory = Inventory.generateInventory(loader);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		loader = null;
		inventory = null;
	}

	/*
	 * @Before public void setUp() throws Exception { }
	 * 
	 * @After public void tearDown() throws Exception { }
	 */

	@Test
	public void testNumberOfItemsInventory() {
		// Mock loader has 3 items
		Long l = new Long(inventory.numberOfInventoryItems());

		assertEquals(3, l.longValue());
	}

	@Test
	public void testTotalSizeOfInventory() {
		// Mock loader has 150 units of items in stock
		Long l = new Long(inventory.totalQuantityOfInventoryItems());

		assertEquals(150, l.longValue());
	}
}
