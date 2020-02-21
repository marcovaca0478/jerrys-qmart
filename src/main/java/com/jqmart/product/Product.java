package com.jqmart.product;

/**
 * Product. The *things* that are sold in the Qmart For the purpose of the
 * exercise, we don't have cost properties, only prices.
 * 
 * @author Marco Vaca
 */
public class Product {

	// Attribute added to simplify adding items to the cart
	private String barCode;

	// List of attributes provided for the exercise
	private String name;
	private double price;
	private double memberPrice;
	private boolean isTaxable;

	// Constants to use in the type of product
	// public static String TAXABLE = "T";
	// public static String EXCEMPT = "E";

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(double memberPrice) {
		this.memberPrice = memberPrice;
	}

	public boolean isTaxable() {
		return isTaxable;
	}

	public void setTaxable(boolean isTaxable) {
		this.isTaxable = isTaxable;
	}
}
