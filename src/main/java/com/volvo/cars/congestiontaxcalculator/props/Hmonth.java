package com.volvo.cars.congestiontaxcalculator.props;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Hmonth {

    private int month;
    List<Integer> dates;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public List<Integer> getDates() {
		return dates;
	}
	public void setDates(List<Integer> dates) {
		this.dates = dates;
	}
    
    
}
