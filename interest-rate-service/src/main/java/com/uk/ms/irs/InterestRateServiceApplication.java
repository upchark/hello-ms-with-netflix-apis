package com.uk.ms.irs;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InterestRateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterestRateServiceApplication.class, args);
	}

	static final Map<Object, Object> interestRates = Arrays.stream(new Object[][] {
		{"HOME", 7d}, {"CAR", 10d}, {"PERSONAL", 12d}
	}).collect(Collectors.toMap(key-> key[0], value -> value[1]));

	//@GetMapping(value = "/interest/loanType/{type}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/interest/loanType/{type}", method = RequestMethod.GET)
	public ResponseEntity<Double> calculateInterestRate(@PathVariable(name = "type") String loanType, HttpServletRequest request) {
		System.out.println("calculateInterestRate"+request.getServerPort());
		Double rate = (Double) interestRates.get(loanType.toUpperCase());
		return new ResponseEntity<>(rate, HttpStatus.OK);
	}

}
