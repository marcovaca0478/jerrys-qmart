package com.jqmart.inventory;

import com.jqmart.product.Product;

/**
 * An Inventory Item. The combination of a product and the quantity available in
 * stock.
 * 
 * @author Marco Vaca
 */
public class InventoryItem {
	private Product product;
	private int quantity;

	// Constructor
	public InventoryItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	// Gets the inner Product
	// TODO: Confirm if used
	public Product getProduct() {
		return product;
	}

	// Commented to prevent access
	/*
	 * public void setProduct(Product product) { this.product = product; }
	 */

	// Actual quantity
	public int getQuantity() {
		return quantity;
	}

	/*
	 * // Used to set the new quantity public void setQuantity(int quantity) {
	 * this.quantity = quantity; }
	 */

	// Decrease stock
	public void decreaseQuantity(int quantityToDecrease) {
		this.quantity = this.quantity - quantityToDecrease;
	}

	// Increase stock
	public void increaseQuantity(int quantityToIncrease) {
		this.quantity = this.quantity + quantityToIncrease;
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
		return this.product.getPrice();
	}

	// Gets the Member Price from the inner product
	public double getMemberPrice() {
		return this.product.getMemberPrice();
	}

	// Gets the taxable status from the inner product
	public boolean isTaxable() {
		return this.product.isTaxable();
	}
}
