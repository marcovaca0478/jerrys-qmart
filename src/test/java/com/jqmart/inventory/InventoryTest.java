package com.jqmart.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jqmart.product.Product;

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

	@Test
	public void testGetANonexistingProduct() {
		// Should return null for this code
		Product p = inventory.getProduct("XXX");

		assertNull(p);
	}

	@Test
	public void testGetAnExistingProduct() {
		// Milk Barcode is "D01", from Mock Loader
		Product p = inventory.getProduct("D01");

		assertEquals("D01", p.getBarCode());
	}

	@Test
	public void testGetStockOfMilk() {
		// Milk has 50 units in stock
		// Barcode is "D01", from Mock Loader
		Long stock = new Long(inventory.getStock("D01"));

		assertEquals(50, stock.longValue());
	}

	@Test
	public void testIncreaseStockOfProduct() {
		int sixRBReturned = 6;

		// Six units of Red Bull have been returned
		inventory.increaseItemStock("D20", sixRBReturned);
		Long currentStock = new Long(inventory.getStock("D20"));

		assertEquals(56, currentStock.longValue());
	}

	@Test
	public void testDecreaseStockOfProduct() {
		int sevenMilksPurchased = 7;

		// Seven units of Milk have been purchased
		assertTrue(inventory.decreaseItemStock("D01", sevenMilksPurchased));
		
		Long currentStock = new Long(inventory.getStock("D01"));

		assertEquals(43, currentStock.longValue());
	}

	@Test
	public void testDecreaseStockOfProductBeyondExistences() {
		int oneHundredMilksPurchased = 100;
		
		assertFalse(inventory.decreaseItemStock("D01", oneHundredMilksPurchased));
		
		Long currentStock = new Long(inventory.getStock("D01"));

		assertEquals(43, currentStock.longValue());
	}
}
