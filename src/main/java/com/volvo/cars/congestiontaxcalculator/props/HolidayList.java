package com.volvo.cars.congestiontaxcalculator.props;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "holidays")
public class HolidayList {

    private List<HYear> years;

	public List<HYear> getYears() {
		return years;
	}

	public void setYears(List<HYear> years) {
		this.years = years;
	}
    
    
}
