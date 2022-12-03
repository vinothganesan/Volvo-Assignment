package com.volvo.cars.congestiontaxcalculator.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.volvo.cars.congestiontaxcalculator.model.MyObject;
import com.volvo.cars.congestiontaxcalculator.model.MyObject2;
import com.volvo.cars.congestiontaxcalculator.props.CostTiming;
import com.volvo.cars.congestiontaxcalculator.props.HYear;
import com.volvo.cars.congestiontaxcalculator.props.Hmonth;
import com.volvo.cars.congestiontaxcalculator.props.HolidayList;
import com.volvo.cars.congestiontaxcalculator.props.TaxExempt;
import com.volvo.cars.congestiontaxcalculator.props.TaxPrice;
import com.volvo.cars.congestiontaxcalculator.props.Vehicle;

@Service
public class CongestionTaxCalculator {

	@Autowired
	TaxExempt taxExempt;

	@Autowired
	TaxPrice taxPrice;

	@Autowired
	HolidayList holidayList;

	@Value("${singlecharge.time}")
	private int singleChargeTime;

	public List<MyObject> getTax(Vehicle vehicle_type, Date[] dates) {

		List<MyObject> object1 = new ArrayList<>();

		if (!IsTollFreeVehicle(vehicle_type.name())) {

			Map<Integer, List<Date>> dateList = Arrays.asList(dates).stream().sorted()
					.collect(Collectors.groupingBy(a -> a.getDate()));

			for (Entry<Integer, List<Date>> entry : dateList.entrySet()) {

				List<Date> date = entry.getValue().stream().sorted().collect(Collectors.toList());
				if (!IsTollFreeDate(date.get(0))) {

					int price = 0;
					int toatlDayprice = 0;
					Date previousDate = null;
					Date loadingDate = null;

					List<MyObject2> objectList = new ArrayList<>();
					for (int index = 0; index < date.size(); index++) {

						Date currentDate = date.get(index);

						if (previousDate == null) {
							previousDate = currentDate;
							loadingDate = currentDate;
							price = getPrice(currentDate);
							toatlDayprice += checkLastDate(index, date, loadingDate, price, objectList);

						} else {

							long secs = (currentDate.getTime() - previousDate.getTime()) / 1000;
							long mins = secs / 60;

							if (mins <= 60) {
								
								int price_2 = getPrice(currentDate);
								if (price_2 >= price) {
									price = price_2;
									loadingDate = currentDate;
								}

								toatlDayprice += checkLastDate(index, date, loadingDate, price, objectList);

							} else {

								MyObject2 object = new MyObject2();
								object.setDate(loadingDate);
								object.setDate2(previousDate);
								object.setPrice(price);
								objectList.add(object);
								previousDate = currentDate;
								loadingDate = currentDate;
								toatlDayprice += price;
								price = getPrice(currentDate);
								toatlDayprice += checkLastDate(index, date, loadingDate, price, objectList);

							}
						}
					}

					MyObject myObject = new MyObject();
					myObject.setPerdayPrice(singleChargeTime);	
					myObject.setDetails(objectList);
					myObject.setTotalPrice(toatlDayprice);
					
					if (toatlDayprice > singleChargeTime) 
						myObject.setPricetoPay(singleChargeTime);
					else
						myObject.setPricetoPay(toatlDayprice);
					
					object1.add(myObject);
					

				}
			}
		}

		return object1;
	}

	private int checkLastDate(int index, List<Date> date, Date loadingDate, int price, List<MyObject2> objectList) {
		
		if (index == date.size() - 1) {

			MyObject2 object = new MyObject2();
			object.setDate(loadingDate);
			object.setDate2(loadingDate);
			object.setPrice(price);
			objectList.add(object);
			return price;
		}
		return 0;
		
	}

	private int getPrice(Date currentDate) {

		String hour = currentDate.getHours() + ":" + currentDate.getMinutes();

		return taxPrice.getTimings().stream().filter(obj -> obj.isTimeBetween(hour)).findFirst()
				.orElse(new CostTiming()).getPrice();

	}

	private boolean IsTollFreeVehicle(String vehicleType) {
		if (vehicleType == null)
			return false;
		return taxExempt.getVehicles().contains(vehicleType);
	}

	private Boolean IsTollFreeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

		if (day == Calendar.SATURDAY || day == Calendar.SUNDAY)
			return true;

		for (HYear hYear : holidayList.getYears()) {
			if (hYear.getYear() == year) {
				for (Hmonth hmonth : hYear.getMonths()) {
					if (hmonth.getMonth() == month)
						if (hmonth.getDates().isEmpty())
							return true;
						else if (hmonth.getDates().contains(dayOfMonth) || hmonth.getDates().contains(dayOfMonth - 1))
							return true;
				}
			}
		}
		return false;
	}

}
