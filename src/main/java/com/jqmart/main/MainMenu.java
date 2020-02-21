package com.jqmart.main;

import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu() {
		while (true) {
			System.out.println("Main Menu");
			System.out.println("1) New transaction");
			System.out.println("2) Exit");

			Scanner scanner = new Scanner(System.in);

			String option = scanner.nextLine();

			switch (option) {
			case "1":
				menuClient();
				break;
			case "2":
				scanner.close();
				return;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}
	}

	private static void menuClient() {
		Scanner scanner;
		System.out.println("Tipo de cliente");
		System.out.println("1) Member");
		System.out.println("2) Regular");

		scanner = new Scanner(System.in);
		String clientType = scanner.nextLine();
		menuWhile: while (true) {
			System.out.println("1) Add product");
			System.out.println("2) Remove product");
			System.out.println("3) Empty cart");
			System.out.println("4) View cart");
			System.out.println("5) Confirm");
			System.out.println("6) Cancel");
			String option = scanner.nextLine();
			switch (option) {
			case "6":
				break menuWhile;
			default:
				System.out.println("Invalid option");
				continue;
			}
		}

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * Inventory inventory = new Inventory();
	 * 
	 * 
	 * while (true) { System.out.println("Main Menu");
	 * System.out.println("1) New transaction"); System.out.println("2) Exit");
	 * 
	 * 
	 * 
	 * System.out.println("Tipo de cliente"); System.out.println("1) Member");
	 * System.out.println("2) Regular");
	 * 
	 * Scanner myObj = new Scanner(System.in); // Create a Scanner object String
	 * customerType = myObj.nextLine();
	 * 
	 * System.out.println("1) Add item to cart");
	 * System.out.println("2) Remove item"); System.out.println("3) Empty cart"); //
	 * System.out.println("4) Cancel order and exit");
	 * 
	 * myObj = new Scanner(System.in); // Create a Scanner object String optionOne =
	 * myObj.nextLine();
	 * 
	 * switch (optionOne) { case "1": List<MenuOption> options =
	 * Menu.getMenuOptions(inventory.getProducts());
	 * 
	 * for (MenuOption menuOption : options) {
	 * System.out.println(menuOption.getFullOption());
	 * 
	 * } break; default: System.out.println("Invalid option"); continue; } }
	 * 
	 * }
	 */
}
