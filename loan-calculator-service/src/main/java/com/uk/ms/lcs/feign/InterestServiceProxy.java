package com.uk.ms.lcs.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name = "interest-rate", url = "localhost:8100/interest-rate-service", decode404 = true)
@FeignClient(name = "interest-rate-service", decode404 = true)
@RibbonClient(name = "interest-rate-service")
public interface InterestServiceProxy {
	
	//@GetMapping(value = "/interest/loanType/{type}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@GetMapping(value = "/interest-rate-service/interest/loanType/{type}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/interest/loanType/{type}", method = RequestMethod.GET)
	public ResponseEntity<Double> getInterestRate(@PathVariable(name = "type") String loanType);

}
