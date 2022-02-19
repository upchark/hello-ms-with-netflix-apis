package com.uk.ms.lcs.domain;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class RequestDetails {

	private String url;

	private HttpMethod httpMethod;

	private Map<String, Object> pathVariable;

	private Map<String, String> requestParam;

	HttpHeaders headers = new HttpHeaders();

	public RequestDetails(String url, HttpMethod httpMethod) {
		super();
		this.url = url;
		this.httpMethod = httpMethod;
		this.headers.add("Authorization", "jwtToken");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public Map<String, Object> getPathVariable() {
		return pathVariable;
	}

	public void setPathVariable(Map<String, Object> pathVariable) {
		this.pathVariable = pathVariable;
	}

	public Map<String, String> getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(Map<String, String> requestParam) {
		this.requestParam = requestParam;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

}
