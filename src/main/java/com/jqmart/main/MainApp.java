package com.jqmart.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jqmart.cart.Cart;
import com.jqmart.cart.CartItem;
import com.jqmart.inventory.DataLoaderStrategy;
import com.jqmart.inventory.FileDataLoader;
import com.jqmart.inventory.Inventory;
import com.jqmart.inventory.InventoryItem;
import com.jqmart.menu.Menu;
import com.jqmart.menu.MenuOption;
import com.jqmart.product.Product;
import com.jqmart.transaction.Transaction;
import com.jqmart.util.TxtFileWriter;
import com.jqmart.util.Utils;

public class MainApp {

	private static DataLoaderStrategy loader;
	private static Inventory inventory;

	private static Scanner scanner;

	private static Cart cart;

	// Starter
	public static void main(String[] args) {
		// Load the Data
		loader = new FileDataLoader();

		// Handle file
		if (1 == args.length) {
			String fileName = args[0];
			inventory = Inventory.generateInventory(loader, fileName);
		} else
			inventory = Inventory.generateInventory(loader);

		mainMenu();
		scanner.close();
	}

	// Main menu
	private static void mainMenu() {
		while (true) {
			System.out.println("Main Menu:");
			System.out.println("1) New sale");
			System.out.println("2) Exit");

			scanner = new Scanner(System.in);

			String option = scanner.nextLine();

			switch (option) {
			case "1":
				menuClient();
				break;
			case "2":
				return;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}
	}

	// Client menu
	private static void menuClient() {
		System.out.println("Enter client type:");
		System.out.println("1) Regular");
		System.out.println("2) Rewards Member");

		scanner = new Scanner(System.in);
		int customerType = new Integer(scanner.nextLine()).intValue();

		// Create a cart
		cart = Cart.createNewCart(customerType);

		// Initiate transactions
		menuWhile: while (true) {
			System.out.println("1) Add product");
			System.out.println("2) Remove product");
			System.out.println("3) Empty cart");
			System.out.println("4) View cart");
			System.out.println("5) Confirm purchase");
			System.out.println("6) Cancel order");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				menuAddProduct();
				break;
			case "2":
				menuRemoveProduct();
				break;
			case "3":
				menuEmptyCart();
				break;
			case "4":
				menuViewCart();
				break;
			case "5":
				menuConfirmPurchase();
				break menuWhile;
			case "6":
				break menuWhile;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}

	}

	// Menu to confirm purchase
	private static void menuConfirmPurchase() {
		if (0 == cart.getItems().size()) {
			System.out.println("Cart is empty!");
		} else {

			menuViewCart();

			menuWhile: while (true) {
				System.out.println("Confirm purchase ? (y/n)");
				String confirmation = scanner.nextLine();

				switch (confirmation) {
				case "y":
					// Create a new Transaction
					Transaction tx = Transaction.createTransaction();
					tx.generateTransaction(cart);

					System.out.println("Cash to pay? (Amount due = " + tx.getTotal() + ")");
					String strCashPaid = scanner.nextLine();

					tx.payTransaction(new Double(strCashPaid).doubleValue());

					// generate file
					try {
						TxtFileWriter.writeFile(tx);
					} catch (IOException e) {
						e.printStackTrace();
					}

					System.out.println("*********************");
					break menuWhile;
				case "n":
					// Do something
					System.out.println("");
					break menuWhile;
				default:
					System.out.println("Invalid option");
					continue;
				}
			}
		}
	}

	// Menu to remove product from Cart
	private static void menuRemoveProduct() {
		menuViewCart();

		while (true) {
			System.out.println("Select the Barcode to remove");
			String barCodeToRemove = scanner.nextLine();

			if (null == inventory.getProduct(barCodeToRemove)) {
				System.out.println("Invalid option");
				continue;
			}

			Product product = inventory.getProduct(barCodeToRemove);
			CartItem ci = new CartItem(product, 0, cart.getCustomerType());

			if (cart.getItems().contains(ci)) {

				List<CartItem> items = cart.getItems();
				for (CartItem cartItem : items) {
					if (barCodeToRemove.equals(cartItem.getBarCode())) {
						// Increase the stock
						inventory.increaseItemStock(barCodeToRemove, cartItem.getQuantityDesired());
					}
				}

				// Remove the item
				cart.removeItem(barCodeToRemove);

				System.out.println("Item removed !");
				break;

			} else {
				System.out.println("Item does not exist in cart");
				continue;
			}
		}
	}

	// Menu to empty the cart (with confirmation)
	private static void menuEmptyCart() {
		menuWhile: while (true) {
			System.out.println("Are you sure you want to empty your Shopping Cart? (y/n)");
			String confirmation = scanner.nextLine();

			switch (confirmation) {
			case "y":
				// Increment the stock for all items
				List<CartItem> items = cart.getItems();
				for (CartItem cartItem : items) {
					// Increase the stock
					inventory.increaseItemStock(cartItem.getBarCode(), cartItem.getQuantityDesired());
				}

				// delete cart
				cart.emptyCart();
				System.out.println("Shopping Cart emptied !");
				break menuWhile;
			case "n":
				// do nothing
				System.out.println("No change to Shopping Cart!");
				break menuWhile;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}
	}

	// Menu to View the Cart
	private static void menuViewCart() {
		System.out.println("------ Shopping Cart ------");
		System.out.println("  (BC)  Item Name           , Qty., Unit Price,   Subtotal");
		List<CartItem> items = cart.getItems();

		for (CartItem item : items) {
			System.out.println("* (" + item.getBarCode() + ") " + Utils.rightPadString(item.getName(), 20) + ", "
					+ Utils.leftPadString(String.valueOf(item.getQuantityDesired()), 4) + ", US$ "
					+ Utils.leftPadString(String.valueOf(item.getPrice()), 6) + ", US$ "
					+ Utils.leftPadString(String.valueOf(item.getSubtotalPerItem()), 6));
		}

		System.out.println("---------------------------");
		System.out.println("");
	}

	// Menu to Add a Product
	private static void menuAddProduct() {

		Map<String, InventoryItem> items = inventory.getItems();

		List<MenuOption> options = Menu.generateMenuFromInventory(items);

		menuWhile: while (true) {
			for (MenuOption menuOption : options) {
				System.out.println(menuOption.getFullOption());
			}

			System.out.println("Choose a product (barCode) to add to the cart:");
			String barCodeToAdd = scanner.nextLine();

			// Verify if barcode is legal
			if (null == inventory.getProduct(barCodeToAdd)) {
				System.out.println("Invalid option");
				continue;
			} else {
				// Add the quantity
				int actualStock = inventory.getStock(barCodeToAdd);
				int quantity = 0;

				while (true) {
					System.out.println("How many? (" + actualStock + ") available:");
					String howMany = scanner.nextLine();

					if ("".equals(howMany) || null == howMany) {
						System.out.println("Enter a quantity!");
						continue;
					}

					try {
						quantity = new Integer(howMany).intValue();
					} catch (NumberFormatException e) {
						System.out.println("Enter a quantity!");
						continue;
					}

					break;
				}

				if (inventory.decreaseItemStock(barCodeToAdd, quantity)) {
					CartItem ci = new CartItem(inventory.getProduct(barCodeToAdd), quantity, cart.getCustomerType());
					cart.addItem(ci);

					System.out.println("Item added succesfully!");
					System.out.println("");
					break menuWhile;
				}

				else
					System.out.println("Not enough stock!");
			}
		}
	}

}
