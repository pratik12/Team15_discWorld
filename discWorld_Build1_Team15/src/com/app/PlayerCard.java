package com.app;

public class PlayerCard {
	
	private String color;
	private String name;
	private String location;

	public PlayerCard(String color, String name, String location){
		
		setColor(color);
		setLocation(location);
		setName(name);

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
