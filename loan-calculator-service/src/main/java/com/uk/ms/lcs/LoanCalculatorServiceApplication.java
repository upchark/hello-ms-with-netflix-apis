package com.uk.ms.lcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.uk.ms.lcs.feign.MyMappingJackson2HttpMessageConverter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.uk.ms.lcs")
@EnableCircuitBreaker
public class LoanCalculatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanCalculatorServiceApplication.class, args);
	}

	//@Bean
	//@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();

	}
	
	@Bean
	public MyMappingJackson2HttpMessageConverter myMappingJackson2HttpMessageConverter() {
		return new com.uk.ms.lcs.feign.MyMappingJackson2HttpMessageConverter();
	}
}
