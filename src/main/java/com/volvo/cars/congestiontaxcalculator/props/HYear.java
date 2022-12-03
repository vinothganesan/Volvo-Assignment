package com.volvo.cars.congestiontaxcalculator.props;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class HYear {

    private int year;
    List<Hmonth> months;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<Hmonth> getMonths() {
		return months;
	}
	public void setMonths(List<Hmonth> months) {
		this.months = months;
	}
    
    

}
