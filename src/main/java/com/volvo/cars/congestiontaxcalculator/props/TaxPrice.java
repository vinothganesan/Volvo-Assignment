package com.volvo.cars.congestiontaxcalculator.props;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tax-price")
public class TaxPrice {

    private List<CostTiming> timings;

	public List<CostTiming> getTimings() {
		return timings;
	}

	public void setTimings(List<CostTiming> timings) {
		this.timings = timings;
	}    
    
}