package com.jqmart.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A shopping Cart.
 * 
 * @author Marco Vaca
 */
public class Cart {

	private int customerType;
	private List<CartItem> items;
	private int totalNumberOfItems;

	// To prevent direct instantiation
	private Cart() {
	}

	// Customer Type
	public int getCustomerType() {
		return customerType;
	}

	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}

	// Item List
	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	// Static creation of a new Cart
	public static Cart createNewCart(int customerType) {
		Cart c = new Cart();

		c.setCustomerType(customerType);
		c.setItems(new ArrayList<CartItem>());

		c.totalNumberOfItems = 0;

		return c;
	}

	// Add item to Cart
	public void addItem(CartItem item) {
		String barCode = item.getBarCode();
		double subtotalForItem = 0.0;
		double savingsForItem = 0.0;

		// Check if quantity > 0
		if (0 == item.getQuantityDesired())
			return;

		// Check if there is no previous instance for this item
		if (this.items.contains(item)) {
			// Iterate to get the item
			Iterator<CartItem> itr = this.items.iterator();

			while (itr.hasNext()) {
				CartItem current = itr.next();
				if (barCode.equals(current.getBarCode())) {
					// add the quantity
					item.setQuantityDesired((item.getQuantityDesired() + current.getQuantityDesired()));

					itr.remove();
				}
			}
		}

		// Calculate the subtotal
		subtotalForItem = item.getQuantityDesired() * item.getPrice();

		// Calculate the savings
		savingsForItem = item.getQuantityDesired() * item.getSavingAmount();

		item.setSubtotalPerItem(subtotalForItem);
		item.setSavingsPerItem(savingsForItem);

		this.items.add(item);

	}

	// Remove item from Cart
	public void removeItem(String barCode) {
		Iterator<CartItem> itr = this.items.iterator();

		while (itr.hasNext()) {
			CartItem current = itr.next();

			if (barCode.equals(current.getBarCode())) {
				itr.remove();
			}
		}
	}

	// Empty Cart
	public void emptyCart() {
		this.items.clear();
	}

	// Total number of items for Cart
	public int getTotalNumberOfItems() {
		return totalNumberOfItems;
	}

}