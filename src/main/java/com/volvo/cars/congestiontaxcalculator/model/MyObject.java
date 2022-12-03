package com.volvo.cars.congestiontaxcalculator.model;

import java.util.List;

public class MyObject {

	long totalPrice;

	long perdayPrice;
	
	long pricetoPay;

	private List<MyObject2> details;

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getPerdayPrice() {
		return perdayPrice;
	}

	public void setPerdayPrice(long perdayPrice) {
		this.perdayPrice = perdayPrice;
	}

	public long getPricetoPay() {
		return pricetoPay;
	}

	public void setPricetoPay(long pricetoPay) {
		this.pricetoPay = pricetoPay;
	}

	public List<MyObject2> getDetails() {
		return details;
	}

	public void setDetails(List<MyObject2> details) {
		this.details = details;
	}

}
