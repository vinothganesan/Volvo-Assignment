# congestion-tax-calculator

congestion-tax-calculator is a Springboot REST Api service to calculate the congestion  tax calculator in a City.

As part of calculation, this applications implemented some of exemption on Holidays, weekends and some of vehicle.

REST Endpoint:

**API URL:** http://localhost:8080/tax/calculator

**API method:** POST

**API Content Type:** application/json

**Input Json:** 

    {
        "vehicle_type": "Car",
        "dates": [
            "2013-01-14 21:00:00","2013-01-15 21:00:00",
            "2013-02-07 06:23:27","2013-02-07 15:27:00",
            "2013-02-08 06:27:00","2013-02-08 06:20:27",
            "2013-02-08 14:35:00","2013-02-08 15:29:00",
            "2013-02-08 15:47:00","2013-02-08 16:01:00",
            "2013-02-08 16:48:00","2013-02-08 17:49:00",
            "2013-02-08 18:29:00","2013-02-08 18:35:00",
            "2013-03-26 14:25:00","2013-03-28 14:07:27"
        ]
    }
   Output: 

       [
		    {
		        "totalPrice": 21,
		        "perdayPrice": 60,
		        "pricetoPay": 21,
		        "details": [
		            {
		                "price": 8,
		                "to": "07-02-2013 06:23:27",
		                "from": "07-02-2013 06:23:27"
		            },
		            {
		                "price": 13,
		                "to": "07-02-2013 15:27:00",
		                "from": "07-02-2013 15:27:00"
		            }
		        ]
		    },
		    {
		        "totalPrice": 70,
		        "perdayPrice": 60,
		        "pricetoPay": 60,
		        "details": [
		            {
		                "price": 8,
		                "to": "08-02-2013 06:27:00",
		                "from": "08-02-2013 06:20:27"
		            },
		            {
		                "price": 13,
		                "to": "08-02-2013 15:29:00",
		                "from": "08-02-2013 14:35:00"
		            },
		            {
		                "price": 18,
		                "to": "08-02-2013 16:01:00",
		                "from": "08-02-2013 15:47:00"
		            },
		            {
		                "price": 18,
		                "to": "08-02-2013 16:48:00",
		                "from": "08-02-2013 16:48:00"
		            },
		            {
		                "price": 13,
		                "to": "08-02-2013 17:49:00",
		                "from": "08-02-2013 17:49:00"
		            }
		        ]
		    },
		    {
		        "totalPrice": 8,
		        "perdayPrice": 60,
		        "pricetoPay": 8,
		        "details": [
		            {
		                "price": 8,
		                "to": "26-03-2013 14:25:00",
		                "from": "26-03-2013 14:25:00"
		            }
		        ]
		    },
		    {
		        "totalPrice": 0,
		        "perdayPrice": 60,
		        "pricetoPay": 0,
		        "details": [
		            {
		                "price": 0,
		                "to": "14-01-2013 21:00:00",
		                "from": "14-01-2013 21:00:00"
		            }
		        ]
		    },
		    {
		        "totalPrice": 0,
		        "perdayPrice": 60,
		        "pricetoPay": 0,
		        "details": [
		            {
		                "price": 0,
		                "to": "15-01-2013 21:00:00",
		                "from": "15-01-2013 21:00:00"
		            }
		        ]
		    }
		]

# Build and Run

### Prerequsit

Java 8 +

Maven

### Clone application 
git clone https://github.com/Volvo-Assignment/congestion-tax-calculator.git

### Build application 

Run the below maven command on the congestion-tax-calculator

**mvn clean install**

### Run application

    java -jar target/congestion-tax-calculator-0.0.1-SNAPSHOT.jar

### Run application with external application.yml

    java -jar target/congestion-tax-calculator-0.0.1-SNAPSHOT.jar --spring.config.location=../application.yml

# Configuration
The application configurations are placed in application.yml (Under src/main/resources)

The tax prices, exempt vehicles, and holidays in a year are configured through the configuration file.
