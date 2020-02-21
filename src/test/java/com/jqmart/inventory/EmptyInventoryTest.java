package com.jqmart.inventory;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests for an empty Inventory.
 * 
 * @author Marco Vaca
 */
public class EmptyInventoryTest {
	private static DataLoaderStrategy loader;
	private static Inventory inventory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loader = new MockEmptyDataLoader();
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
	public void testEmptyInventory() {
		// Empty loader has 0 items
		Long l = new Long(inventory.numberOfInventoryItems());

		assertEquals(0, l.longValue());
	}
}
