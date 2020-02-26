package com.jqmart.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jqmart.cart.Cart;
import com.jqmart.cart.CartItem;
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
public class TransactionTest {
	private static DataLoaderStrategy loader;
	private static Inventory inventory;
	private static Transaction t;

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

	@Before
	public void setUp() throws Exception {
		// Create the Transaction
		t = Transaction.createTransaction();

		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REGULAR_CUSTOMER);

		// Add an item
		Product product = inventory.getProduct("D01");
		CartItem ci = new CartItem(product, 3, c.getCustomerType());
		c.addItem(ci);

		product = inventory.getProduct("D20");
		ci = new CartItem(product, 5, c.getCustomerType());
		c.addItem(ci);

		product = inventory.getProduct("G06");
		ci = new CartItem(product, 7, c.getCustomerType());
		c.addItem(ci);

		// Verify there are 3 items for the Transaction
		t.generateTransaction(c);
	}

	@After
	public void tearDown() throws Exception {
		t = null;
	}

	@Test
	public void testCreateTransaction() {
		// Create the Transaction
		Transaction tx = Transaction.createTransaction();

		// Verify it is empty
		Long size = new Long(tx.getItems().size());
		assertEquals(0, size.longValue());

		// Verify all other values are zero
		assertEquals(0, tx.getTotalNumberOfItems());
		assertEquals(0.0, tx.getNonTaxableSubtotal(), 0);
		assertEquals(0.0, tx.getTaxableSubtotal(), 0);
		assertEquals(0.0, tx.getTax(), 0);
		assertEquals(0.0, tx.getNonTaxableSubtotal(), 0);
		assertEquals(0.0, tx.getAmountPaid(), 0);
		assertEquals(0.0, tx.getChange(), 0);
	}

	@Test
	public void testGenerateTransaction() {
		Long size = new Long(t.getItems().size());
		assertEquals(3, size.longValue());

		// Verify the total number of items
		assertEquals(15, t.getTotalNumberOfItems());

		// Verify the NONTAXABLE subtotal
		assertEquals(32.95, t.getNonTaxableSubtotal(), 0.0);

		// Verify the total amount
		assertEquals(55.85, t.getTotal(), 0.0);
	}

	@Test
	public void testPayTransaction() {
		double cashPaid = 60.0;

		// Verify the payment
		t.payTransaction(cashPaid);

		// Verify the change
		assertEquals(60.0, t.getAmountPaid(), 0.0);
		assertEquals(4.15, t.getChange(), 0.0);
	}

	@Test
	public void testTaxableSubtotal() {

		double taxSubTotal = t.getTaxableSubtotal();

		// Verify proper Subtotal
		assertEquals(21.5, taxSubTotal, 0);
	}

	@Test
	public void testTax() {
		double tax = t.getTax();

		// Verify tax amount
		assertEquals(1.40, tax, 0.0);
	}

	@Test
	public void testSubtotalForRegular() {
		double subTotal = t.getSubtotal();

		// Verify Subtotal
		assertEquals(54.45, subTotal, 0);
	}

	@Test
	public void testSubtotalForMember() {
		// Create new cart for a regular customer
		Cart c = Cart.createNewCart(Customer.REWARDS_MEMBER);

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

		// Verify there are 3 items for the Transaction
		t.generateTransaction(c);

		double subTotal = t.getSubtotal();

		// Verify Subtotal is not the same for regular
		assertNotEquals(54.45, subTotal, 0);

		// Verify proper Subtotal
		assertEquals(49.75, subTotal, 0);
	}
}
