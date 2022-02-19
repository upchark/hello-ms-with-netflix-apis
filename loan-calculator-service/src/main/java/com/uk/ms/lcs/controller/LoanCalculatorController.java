package com.uk.ms.lcs.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.uk.ms.lcs.domain.LoanCalculatorRequest;
import com.uk.ms.lcs.domain.LoanCalculatorResponse;
import com.uk.ms.lcs.domain.RequestDetails;
import com.uk.ms.lcs.feign.InterestServiceProxy;
import com.uk.ms.lcs.service.GenericRestClientService;
import com.uk.ms.lcs.util.LoanCalculator;

@RestController
public class LoanCalculatorController {

	// @Autowired
	private GenericRestClientService genericRestClientService;

	@Autowired
	private InterestServiceProxy interestServiceProxy;

	// @GetMapping(value = "/loan/calculateLoan", consumes =
	// MediaType.APPLICATION_JSON_VALUE, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/loan/calculateLoan")
	public ResponseEntity<LoanCalculatorResponse> getLoanDetailsFeign(
			@RequestBody LoanCalculatorRequest loanCalcRequest) {
		ResponseEntity<Double> responseEntity = interestServiceProxy.getInterestRate(loanCalcRequest.getLoanType());
		System.out.println(responseEntity.getBody().doubleValue());
		double interestRate = responseEntity.getBody().doubleValue();
		LoanCalculatorResponse loanCalculatorResponse = LoanCalculator.calculateLoan(loanCalcRequest.getLoanAmount(),
				loanCalcRequest.getTenure(), interestRate, loanCalcRequest.getLoanType());
		return new ResponseEntity<>(loanCalculatorResponse, HttpStatus.OK);
	}

	@GetMapping(value = "/loan/calculateLoan2")
	public ResponseEntity<LoanCalculatorResponse> getLoanDetails(@RequestBody LoanCalculatorRequest loanCalcRequest) {
		RequestDetails requestDetails = new RequestDetails("http://localhost:8100/irs/interest/loanType/{type}",
				HttpMethod.GET);
		Map<String, Object> pathVariables = new HashMap<>();
		pathVariables.put("type", loanCalcRequest.getLoanType());
		requestDetails.setPathVariable(pathVariables);
		Double interestRate = genericRestClientService.execute(requestDetails, null, Double.class, null);
		// double interestRate = 10;
		System.out.println(interestRate.doubleValue());
		LoanCalculatorResponse loanCalculatorResponse = LoanCalculator.calculateLoan(loanCalcRequest.getLoanAmount(),
				loanCalcRequest.getTenure(), interestRate, loanCalcRequest.getLoanType());
		return new ResponseEntity<>(loanCalculatorResponse, HttpStatus.OK);
	}

	@GetMapping(value = "/loan/loanTypes")
	@HystrixCommand(fallbackMethod = "defaultLoanTypes")
	public ResponseEntity<List<String>> getLoanTypes(@RequestParam(name = "random", required = false) String random) throws NullPointerException {
		List<String> loans = new ArrayList<>();
		if (null != random) {
			for (LoanType loan : LoanType.values()) {
				loans.add(loan.name());
			}
		} else
			throw new NullPointerException();
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}

	public ResponseEntity<List<String>> defaultLoanTypes(String random) {
		List<String> loans = Arrays.asList("Home");
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}

}

enum LoanType {
	HOME, CAR, PERSONAL, GOLD
}
