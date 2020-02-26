package com.jqmart.cart;

import com.jqmart.customer.Customer;
import com.jqmart.product.Product;

/**
 * An item in the Shopping Cart
 * 
 * @author Marco Vaca
 */
public class CartItem {

	private int customerType;
	private Product product;
	private int quantityDesired;
	private double subtotalPerItem;
	private double savingsPerItem;

	// Constructor
	public CartItem(Product product, int quantityDesired, int customerType) {
		this.product = product;
		this.quantityDesired = quantityDesired;
		this.customerType = customerType;
	}

	public Product getProduct() {
		return this.product;
	}

	// Current quantity of items
	public int getQuantityDesired() {
		return quantityDesired;
	}

	// Current quantity of items
	public void setQuantityDesired(int newQuantity) {
		this.quantityDesired = newQuantity;
	}

	// Get subtotal for item
	public double getSubtotalPerItem() {
		return this.subtotalPerItem;
	}

	// Set subtotal for item
	public void setSubtotalPerItem(double subtotalPerItem) {
		this.subtotalPerItem = subtotalPerItem;
	}

	// Get subtotal for item
	public double getSavingsPerItem() {
		return this.savingsPerItem;
	}

	// Set subtotal for item
	public void setSavingsPerItem(double savingsPerItem) {
		this.savingsPerItem = savingsPerItem;
	}

	// Equals method overrided for Cart Item. We only verify that its the same item
	// via Bar Code
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		CartItem other = (CartItem) obj;

		if (product == null) {
			if (other.product != null)
				return false;
		} else if (other.getBarCode() != this.getBarCode())
			return false;

		return true;
	}

	// --- INNER PRODUCT METHODS

	// Gets the bar Code from the inner product
	public String getBarCode() {
		return this.product.getBarCode();
	}

	// Gets the name from the inner product
	public String getName() {
		return this.product.getName();
	}

	// Gets the price from the inner product
	public double getPrice() {
		if (Customer.REGULAR_CUSTOMER == this.customerType)
			return this.product.getPrice();
		else
			return this.product.getMemberPrice();
	}

	// Gets the savings amount
	public double getSavingAmount() {
		if (Customer.REGULAR_CUSTOMER == this.customerType)
			return 0.0;
		else
			return (this.product.getPrice() - this.product.getMemberPrice());
	}

	// Gets the taxable status from the inner product
	public boolean isTaxable() {
		return this.product.isTaxable();
	}
}
