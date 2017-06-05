package com.exrate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.exrate.dto.ExchangeResponseDTO;
import com.exrate.entity.OpenExchangeModel;

@Component
public class ExRateRepository {
	
	@Autowired
	RestTemplate restTemplate;
	
	public OpenExchangeModel getExchangeRate(String url)
	{
		return restTemplate.getForObject(url, OpenExchangeModel.class);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
