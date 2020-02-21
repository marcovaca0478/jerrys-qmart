package menu;

import java.util.ArrayList;
import java.util.List;

import com.jqmart.product.Product;

public class Menu {
	
	public static List<MenuOption> getMenuOptions(List<Product> products) {
		List<MenuOption> options = new ArrayList<MenuOption>();
		
		products.forEach(product -> options.add(new MenuOption(product.getBarCode(), product.getName())));
		
		return options;
	}
}
