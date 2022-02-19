package com.uk.ms.lcs.service;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.uk.ms.lcs.domain.RequestDetails;

@Service
public class GenericRestClientService {

	//@Autowired
	//@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	public <V> V execute(RequestDetails requestDetails, Object body, Class<V> genericClass,
			ResponseErrorHandler errorHandler) {
		hasErrorHandler(errorHandler);
		UriComponentsBuilder ub = UriComponentsBuilder.fromHttpUrl(requestDetails.getUrl());
		//hasPathVariables(requestDetails.getPathVariable(), ub);
		hasRequestParam(requestDetails.getRequestParam(), ub);
		HttpEntity<?> entity = getHttpEntity(requestDetails, body);
		URI uri = ub.buildAndExpand(requestDetails.getPathVariable()).toUri();
		ResponseEntity<V> response = restTemplate.exchange(uri, requestDetails.getHttpMethod(), entity, genericClass);
		return response.getBody();
	}

	private HttpEntity<?> getHttpEntity(RequestDetails requestDetails, Object body) {
		HttpHeaders headers = requestDetails.getHeaders();
		if (Objects.isNull(headers.getContentType()))
			headers.setContentType(MediaType.APPLICATION_JSON);
		if (Objects.isNull(body))
			return new HttpEntity<>(headers);
		return new HttpEntity<>(body, headers);
	}

	private void hasRequestParam(Map<String, String> requestParam, UriComponentsBuilder ub) {
		if (Objects.nonNull(requestParam)) {
			for (Entry<String, String> entry : requestParam.entrySet()) {
				ub.queryParam(entry.getKey(), entry.getValue());
			}
		}
	}

	/*
	 * private void hasPathVariables(Map<String, Object> pathVariable,
	 * UriComponentsBuilder ub) { if (Objects.nonNull(pathVariable)) { //
	 * ub.uriVariables(pathVariable); for (Map.Entry<String, Object> entry :
	 * pathVariable.entrySet()) { } } }
	 */

	private void hasErrorHandler(ResponseErrorHandler errorHandler) {
		if (Objects.nonNull(errorHandler)) {
			restTemplate.setErrorHandler(errorHandler);
		}
	}
}
