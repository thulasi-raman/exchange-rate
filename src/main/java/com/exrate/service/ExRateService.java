package com.exrate.service;

import com.exrate.dto.ExchangeResponseDTO;

public interface ExRateService {
	
	public ExchangeResponseDTO getExchangeRateResponse(String requestType, String date);

}
