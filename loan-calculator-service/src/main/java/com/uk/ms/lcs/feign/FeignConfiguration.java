package com.uk.ms.lcs.feign;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;

@Configuration
public class FeignConfiguration {
	
	@Bean
	public Client feignClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{
		return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
	}
	
	public SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		TrustStrategy trustStrategy = new TrustSelfSignedStrategy();
		SSLContext sslContexts = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build();
		return sslContexts.getSocketFactory();
	}

}
