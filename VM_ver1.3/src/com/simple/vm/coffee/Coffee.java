package com.simple.vm.coffee;

public class Coffee {
	private int water;
	private int coffee;
	private int cream;
	private int sugar;
	private int cup;
	
	public Coffee() {
		// TODO Auto-generated constructor stub
	}
	
	public Coffee(int water, int coffee, int cream, int sugar, int cup) {
		this.water = water;
		this.coffee = coffee;
		this.cream = cream;
		this.sugar = sugar;
		this.cup = cup;
	}

	public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getCoffee() {
		return coffee;
	}
	public void setCoffee(int coffee) {
		this.coffee = coffee;
	}
	public int getCream() {
		return cream;
	}
	public void setCream(int cream) {
		this.cream = cream;
	}
	public int getSugar() {
		return sugar;
	}
	public void setSugar(int sugar) {
		this.sugar = sugar;
	}	

	public int getCup() {
		return cup;
	}

	public void setCup(int cup) {
		this.cup = cup;
	}
	
	@Override
	public String toString() {
		return "Coffee [water=" + water + ", coffee=" + coffee + ", cream=" + cream + ", sugar=" + sugar + ", cup="
				+ cup + "]";
	}
}
