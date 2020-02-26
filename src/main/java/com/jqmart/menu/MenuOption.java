package com.jqmart.menu;

/**
 * A Menu Option.
 * 
 * @author Marco Vaca
 *
 */
public class MenuOption {

	private String menuOptionId;
	private String menuOptionText;

	public MenuOption(String menuOptionId, String menuOptionText) {
		super();
		this.menuOptionId = menuOptionId;
		this.menuOptionText = menuOptionText;
	}

	public String getMenuOptionId() {
		return menuOptionId;
	}

	public void setMenuOptionId(String menuOptionId) {
		this.menuOptionId = menuOptionId;
	}

	public String getMenuOptionText() {
		return menuOptionText;
	}

	public void setMenuOptionText(String menuOptionText) {
		this.menuOptionText = menuOptionText;
	}

	public String getFullOption() {
		return this.getMenuOptionId() + ") " + this.getMenuOptionText();
	}
}
