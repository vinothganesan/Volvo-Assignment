package com.volvo.cars.congestiontaxcalculator.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.volvo.cars.congestiontaxcalculator.core.CongestionTaxCalculator;
import com.volvo.cars.congestiontaxcalculator.model.MyObject;
import com.volvo.cars.congestiontaxcalculator.model.TaxCalculatorRequest;
import com.volvo.cars.congestiontaxcalculator.model.TaxCalculatorResponse;


@RestController
@RequestMapping("api/tax")
public class TaxCalculatorApplicationControllor {

    
    @Autowired
    CongestionTaxCalculator congestionTaxCalculator;

    
    @RequestMapping(value = "/calculator", method = RequestMethod.POST)
    public ResponseEntity<Object> taxCalculate(@RequestBody TaxCalculatorRequest request) {
        TaxCalculatorResponse response;
        try {
            List<MyObject> tax = congestionTaxCalculator.getTax(request.getVehicle_type(), request.getDates());
            return new ResponseEntity<>(tax, HttpStatus.OK);
        } catch (Exception e) {
            response = new TaxCalculatorResponse(e.getLocalizedMessage(), 0,
                    "Tax calculation failed.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
