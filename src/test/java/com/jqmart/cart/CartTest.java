package com.jqmart.cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jqmart.customer.Customer;
import com.jqmart.inventory.DataLoaderStrategy;
import com.jqmart.inventory.Inventory;
import com.jqmart.inventory.MockDataLoader;
import com.jqmart.product.Product;

/**
 * Unit tests for Inventory.
 * 
 * @author Marco Vaca
 */
public class CartTest {
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
	public void testCreateNewCart() {
		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REGULAR_CUSTOMER);

		// Verify it is empty
		Long size = new Long(c.getItems().size());
		assertEquals(0, size.longValue());

		// Verify all other values are zero
		assertEquals(0.0, c.getTotalNumberOfItems(), 0);
	}

	@Test
	public void testAddItemToCart() {
		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REGULAR_CUSTOMER);

		// Add an item
		Product product = inventory.getProduct("D01");
		CartItem ci = new CartItem(product, 3, c.getCustomerType());

		c.addItem(ci);

		// Verify Cart is now size 1
		Long size = new Long(c.getItems().size());
		assertEquals(1, size.longValue());
	}

	@Test
	public void testAddSameItemToCart() {
		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REGULAR_CUSTOMER);

		// Add an item
		Product product = inventory.getProduct("D01");
		CartItem ci = new CartItem(product, 3, c.getCustomerType());

		c.addItem(ci);

		// Add the same item, different quantity
		// Should ADD the quantity
		ci = new CartItem(product, 5, c.getCustomerType());

		c.addItem(ci);

		// Verify Cart is now size 1
		Long size = new Long(c.getItems().size());
		assertEquals(1, size.longValue());
		assertEquals(8, ci.getQuantityDesired());
	}

	@Test
	public void testAddItemWithZeroQuantityToCart() {
		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REGULAR_CUSTOMER);

		// Add an item
		Product product = inventory.getProduct("D01");
		CartItem ci = new CartItem(product, 0, c.getCustomerType());

		c.addItem(ci);

		// Verify Cart is still empty
		Long size = new Long(c.getItems().size());
		assertEquals(0, size.longValue());
	}

	@Test
	public void testAddItemWithOverflowingQuantityToCart() {
		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REGULAR_CUSTOMER);

		// Add an item
		Product product = inventory.getProduct("G06");
		CartItem ci = new CartItem(product, 100, c.getCustomerType());

		// Check stock
		if (inventory.decreaseItemStock("G06", 100)) {
			fail("It's not possible to add the item. Should stop");
			c.addItem(ci);
		} else {
			// Verify Cart is still empty
			Long size = new Long(c.getItems().size());
			assertEquals(0, size.longValue());
		}
	}

	@Test
	public void testRemoveItemFromCart() {
		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REGULAR_CUSTOMER);

		// Add an item
		Product product = inventory.getProduct("D01");
		CartItem ci = new CartItem(product, 3, c.getCustomerType());
		// CartItem ci = new CartItem("D01", 3);
		c.addItem(ci);

		product = inventory.getProduct("D20");
		ci = new CartItem(product, 5, c.getCustomerType());
		// ci = new CartItem("D20", 5);
		c.addItem(ci);

		product = inventory.getProduct("G06");
		ci = new CartItem(product, 7, c.getCustomerType());
		// ci = new CartItem("G06", 7);
		c.addItem(ci);

		// Verify Cart is now size 3
		Long size = new Long(c.getItems().size());
		assertEquals(3, size.longValue());

		c.removeItem("D20");

		// Verify Cart has one less item
		size = new Long(c.getItems().size());
		assertEquals(2, size.longValue());

		/*
		 * List<CartItem> items = c.getItems(); for (CartItem cartItem : items) {
		 * System.out.println(cartItem.getBarCode()); }
		 */
	}

	@Test
	public void testEmptyCart() {
		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REGULAR_CUSTOMER);

		// Add an item
		Product product = inventory.getProduct("D01");
		CartItem ci = new CartItem(product, 3, c.getCustomerType());
		// CartItem ci = new CartItem("D01", 3);
		c.addItem(ci);

		product = inventory.getProduct("D20");
		ci = new CartItem(product, 5, c.getCustomerType());
		// ci = new CartItem("D20", 5);
		c.addItem(ci);

		product = inventory.getProduct("G06");
		ci = new CartItem(product, 7, c.getCustomerType());
		// ci = new CartItem("G06", 7);
		c.addItem(ci);

		// Verify Cart is now size 3
		Long size = new Long(c.getItems().size());
		assertEquals(3, size.longValue());

		c.emptyCart();

		// Verify Cart is empty (size 0)
		size = new Long(c.getItems().size());
		assertEquals(0, size.longValue());
	}

}
