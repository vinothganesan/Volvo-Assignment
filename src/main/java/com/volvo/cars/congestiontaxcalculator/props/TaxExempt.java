package com.volvo.cars.congestiontaxcalculator.props;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tax-exempt")

public class TaxExempt {

    private Set<String> vehicles;

	public Set<String> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<String> vehicles) {
		this.vehicles = vehicles;
	}
    
    

}
