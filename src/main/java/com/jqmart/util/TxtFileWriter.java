package com.jqmart.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jqmart.transaction.Transaction;
import com.jqmart.transaction.TransactionItem;

/**
 * Simple file Writer used to generate the sales receipts.
 * 
 * @author Marco Vaca
 */
public class TxtFileWriter {
	// File Writer
	public static void writeFile(Transaction tx) throws IOException {
		String fileName = tx.getPrintableNumber() + ".txt";

		FileWriter writer = new FileWriter(fileName, true);

		writer.write("File: " + tx.getPrintableNumber() + "\n");
		writer.write(tx.getDate() + "\n");
		writer.write("TRANSACTION: " + tx.getNumber() + "\n");
		writer.write("\n");
		writer.write("---------------------------------------");
		writer.write("\n");

		writer.write(generateList(tx));

		writer.write("\n");
		writer.write("TOTAL NUMBER OF ITEMS SOLD: " + tx.getTotalNumberOfItems() + "\n");
		writer.write("SUB-TOTAL: US$ " + tx.getSubtotal() + "\n");
		writer.write("TAX (6.5%): US$ " + tx.getTax() + "\n");
		writer.write("TOTAL: US$ " + tx.getTotal() + "\n");
		writer.write("\n");
		writer.write("CASH: " + tx.getAmountPaid() + "\n");
		writer.write("CHANGE: " + tx.getChange() + "\n");
		writer.write("*********************************\n");
		writer.write("YOU SAVED: US$ " + tx.getTotalSavings() + "\n");

		writer.close();
	}

	// List of items generator
	private static String generateList(Transaction tx) {
		StringBuilder str = new StringBuilder();

		str.append("(BC)  Item Name           , Qty., Unit Price,   Subtotal");
		str.append("\n");
		List<TransactionItem> items = tx.getItems();

		for (TransactionItem item : items) {
			str.append("(" + item.getBarCode() + ") " + Utils.rightPadString(item.getName(), 20) + ", "
					+ Utils.leftPadString(String.valueOf(item.getQuantityPurchased()), 4) + ", US$ "
					+ Utils.leftPadString(String.valueOf(item.getPrice()), 6) + ", US$ "
					+ Utils.leftPadString(String.valueOf(item.getSubtotalPerItem()), 6));
			str.append("\n");
		}

		str.append("\n");
		str.append("---------------------------------------");
		str.append("\n");

		return str.toString();
	}
}
