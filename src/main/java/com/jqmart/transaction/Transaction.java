package com.jqmart.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jqmart.cart.Cart;
import com.jqmart.cart.CartItem;
import com.jqmart.tax.TaxConstant;
import com.jqmart.util.Utils;

/**
 * A purchase Transaction. Generated after confirmation of purchase
 * 
 * @author Marco Vaca
 */
public class Transaction {

	private String txNumber;
	private String printableNumber;
	private Date date;
	private List<TransactionItem> items;
	private int totalNumberOfItems;
	private double nonTaxableSubtotal;
	private double taxableSubtotal;
	private double tax;
	private double subtotal;
	private double total;
	private double totalSavings;

	private double amountPaid;
	private double change;

	// Private constructor to prevent direct instantiation
	private Transaction() {
	}

	public List<TransactionItem> getItems() {
		return items;
	}

	public void setItems(List<TransactionItem> items) {
		this.items = items;
	}

	// ---------- Accessory methods ----------

	public String getNumber() {
		return txNumber;
	}

	public void setNumber(String number) {
		this.txNumber = number;
	}

	public String getPrintableNumber() {
		return printableNumber;
	}

	public void setPrintableNumber(String printableNumber) {
		this.printableNumber = printableNumber;
	}

	public String getDate() {
		return Utils.formatDate(this.date, "MMMM dd, yyyy");
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotalNumberOfItems() {
		return totalNumberOfItems;
	}

	public void setTotalNumberOfItems(int totalNumberOfItems) {
		this.totalNumberOfItems = totalNumberOfItems;
	}

	public double getNonTaxableSubtotal() {
		return nonTaxableSubtotal;
	}

	public void setNonTaxableSubtotal(double nonTaxableSubtotal) {
		this.nonTaxableSubtotal = nonTaxableSubtotal;
	}

	public double getTaxableSubtotal() {
		return taxableSubtotal;
	}

	public void setTaxableSubtotal(double taxableSubtotal) {
		this.taxableSubtotal = taxableSubtotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalSavings() {
		return totalSavings;
	}

	public void setTotalSavings(double totalSavings) {
		this.totalSavings = totalSavings;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	// Generate a new transaction
	public static Transaction createTransaction() {
		Transaction tx = new Transaction();

		// TX Number
		int nextNumber = IdGenerator.getInstance().generate();
		String txNumber = Utils.padWithZeros("" + nextNumber, 6);
		String txDate = Utils.getDateOfTodayFormatted("ddMMyyyy");

		tx.printableNumber = "tx_" + txNumber + "_" + txDate;
		tx.txNumber = txNumber;

		tx.date = new Date();
		tx.items = new ArrayList<TransactionItem>();
		tx.totalNumberOfItems = 0;
		tx.nonTaxableSubtotal = 0.0;
		tx.taxableSubtotal = 0.0;
		tx.tax = 0.0;
		tx.subtotal = 0.0;
		tx.total = 0.0;
		tx.totalSavings = 0.0;
		tx.amountPaid = 0.0;
		tx.change = 0.0;

		return tx;
	}

	// ---------- Untested ----------

	// Generate the new transaction
	public void generateTransaction(Cart cart) {
		// Items from cart
		this.items = copyItemsFromCart(cart);

		this.totalNumberOfItems = calculateTotalNumberOfItems();
		this.nonTaxableSubtotal = calculateNonTaxableSubTotal();
		this.taxableSubtotal = calculateTaxableSubTotal();
		this.tax = calculateTax(taxableSubtotal);
		this.subtotal = calculateSubTotal();
		this.total = this.subtotal + this.tax;
		this.totalSavings = calculateSavings();

//		System.out.println("no items: " + this.totalNumberOfItems);
//		System.out.println("subt items: " + this.taxableSubtotal);
//		System.out.println("tax: " + this.tax);
//		System.out.println("nontax: " + this.nonTaxableSubtotal);
//		System.out.println("subt usd: " + this.subtotal);
//		System.out.println("total usd: " + this.total);
	}

	// Pay for the transaction
	public void payTransaction(double cashPaid) {
		this.amountPaid = cashPaid;
		this.change = Utils.roundToTwoDecimals(cashPaid - this.total);

//		System.out.println("cash paid: " + this.amountPaid);
//		System.out.println("change: " + this.change);
	}

	// Calculate total number of items in the cart
	private int calculateTotalNumberOfItems() {
		int numberOfItems = 0;

		for (TransactionItem txItem : this.getItems()) {
			numberOfItems = numberOfItems + txItem.getQuantityPurchased();
		}

		return numberOfItems;
	}

	// Total savings for this transaction
	private double calculateSavings() {
		double savings = 0.0;

		for (TransactionItem txItem : this.getItems()) {
			savings = savings + txItem.getSavingsPerItem();
		}

		return Utils.roundToTwoDecimals(savings);
	}

	// Copy Cart Items
	private List<TransactionItem> copyItemsFromCart(Cart cart) {
		List<TransactionItem> purchasedItems = new ArrayList<TransactionItem>();

		for (CartItem cartItem : cart.getItems()) {
			TransactionItem txItem = new TransactionItem(cartItem.getProduct(), cartItem.getQuantityDesired(),
					cartItem.getSubtotalPerItem(), cartItem.getSavingsPerItem());

			purchasedItems.add(txItem);
		}

		return purchasedItems;
	}

	// Calculate the subtotal for the Cart items
	private double calculateSubTotal() {
		double subTotal = 0.0;
		subTotal = this.nonTaxableSubtotal + this.taxableSubtotal;

		return Utils.roundToTwoDecimals(subTotal);
	}

	// Calculate the NON taxable subtotal for the Cart items
	private double calculateNonTaxableSubTotal() {
		double nonTaxableSubTotal = 0.0;

		for (TransactionItem txItem : this.getItems()) {
			if (!txItem.isTaxable())
				nonTaxableSubTotal = nonTaxableSubTotal + txItem.getSubtotalPerItem();
		}

		return Utils.roundToTwoDecimals(nonTaxableSubTotal);
	}

	// Calculate the taxable subtotal for the Cart items
	private double calculateTaxableSubTotal() {
		double taxableSubTotal = 0.0;

		for (TransactionItem txItem : this.getItems()) {
			if (txItem.isTaxable())
				taxableSubTotal = taxableSubTotal + txItem.getSubtotalPerItem();
		}

		return Utils.roundToTwoDecimals(taxableSubTotal);
	}

	// Calculate the tax due for the Cart
	private double calculateTax(double taxableSubTotal) {
		double tax = Utils.roundToTwoDecimals((taxableSubTotal * TaxConstant.TAX_RATE));
		return Utils.roundToTwoDecimals(tax);
	}

}
