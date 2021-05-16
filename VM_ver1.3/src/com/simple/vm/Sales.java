package com.simple.vm;

import java.util.Date;

public class Sales {
	private String pCode;
	private Date date;
	private int price;
	
	public Sales() {
		// TODO Auto-generated constructor stub
	}

	public Sales(String pCode, Date date, int price) {
		super();
		this.pCode = pCode;
		this.date = date;
		this.price = price;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Sales [pCode=" + pCode + ", date=" + date + ", price=" + price + "]";
	}
	
}
