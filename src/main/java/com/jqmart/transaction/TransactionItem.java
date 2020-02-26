package com.jqmart.transaction;

import com.jqmart.product.Product;

/**
 * An item within a Transaction
 * 
 * @author Marco Vaca
 */
public class TransactionItem {
	private Product product;
	private int quantityPurchased;
	private double subtotalPerItem;
	private double savingsPerItem;

	// Constructor
	public TransactionItem(Product product, int quantityPurchased, double subtotalPerItem, double savingsPerItem) {
		this.product = product;
		this.quantityPurchased = quantityPurchased;
		this.subtotalPerItem = subtotalPerItem;
		this.savingsPerItem = savingsPerItem;
	}

	// Actual quantity
	public int getQuantityPurchased() {
		return quantityPurchased;
	}

	// Subtotal per item
	public double getSubtotalPerItem() {
		return subtotalPerItem;
	}

	// Savings per item
	public double getSavingsPerItem() {
		return savingsPerItem;
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
