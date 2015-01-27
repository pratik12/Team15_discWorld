package com.app;

public class Player {
	
	private String minion_Color;
	private int minion_Quantity;
	
	public Player(String minion_Color, int quantity){
		set_Minion_Color(minion_Color);
		set_Minion_Quantity(quantity);
	}
	
	public String get_Minion_Color() {
		return minion_Color;
	}
	
	public void set_Minion_Color(String minion_Color) {
		this.minion_Color = minion_Color;
	}
	
	public int get_Minion_Quantity() {
		return minion_Quantity;
	}
	
	public void set_Minion_Quantity(int minion_Quantity) {
		this.minion_Quantity = minion_Quantity;
	}
	
	

}
