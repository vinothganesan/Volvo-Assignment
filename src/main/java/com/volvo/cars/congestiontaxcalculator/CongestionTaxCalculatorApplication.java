package com.volvo.cars.congestiontaxcalculator;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class CongestionTaxCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CongestionTaxCalculatorApplication.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setTimeZone(TimeZone.getDefault());
		return objectMapper;
	}
	
}
