package com.simple.vm;

public class Money {
	
	private int coin500;
	private int coin100;
	private int coin50;
	private int coin10;

	private int cash10000;
	private int cash5000;
	private int cash1000;
	
	public Money() {
		// TODO Auto-generated constructor stub
	}

	public Money(int coin500, int coin100, int coin50, int coin10, int cash10000, int cash5000, int cash1000) {
		super();
		this.coin500 = coin500;
		this.coin100 = coin100;
		this.coin50 = coin50;
		this.coin10 = coin10;
		this.cash10000 = cash10000;
		this.cash5000 = cash5000;
		this.cash1000 = cash1000;
	}

	public int getCoin500() {
		return coin500;
	}

	public void setCoin500(int coin500) {
		this.coin500 = coin500;
	}

	public int getCoin100() {
		return coin100;
	}

	public void setCoin100(int coin100) {
		this.coin100 = coin100;
	}

	public int getCoin50() {
		return coin50;
	}

	public void setCoin50(int coin50) {
		this.coin50 = coin50;
	}

	public int getCoin10() {
		return coin10;
	}

	public void setCoin10(int coin10) {
		this.coin10 = coin10;
	}

	public int getCash10000() {
		return cash10000;
	}

	public void setCash10000(int cash10000) {
		this.cash10000 = cash10000;
	}

	public int getCash5000() {
		return cash5000;
	}

	public void setCash5000(int cash5000) {
		this.cash5000 = cash5000;
	}

	public int getCash1000() {
		return cash1000;
	}

	public void setCash1000(int cash1000) {
		this.cash1000 = cash1000;
	}

	@Override
	public String toString() {
		return "Money [coin500=" + coin500 + ", coin100=" + coin100 + ", coin50=" + coin50 + ", coin10=" + coin10
				+ ", cash10000=" + cash10000 + ", cash5000=" + cash5000 + ", cash1000=" + cash1000 + "]";
	}
}
