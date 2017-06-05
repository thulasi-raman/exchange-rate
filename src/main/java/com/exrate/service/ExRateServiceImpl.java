package com.exrate.service;



import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exrate.app.CustomProperties;
import com.exrate.dto.ExchangeResponseDTO;
import com.exrate.dto.RatesDTO;
import com.exrate.entity.ExchangeRates;
import com.exrate.entity.OpenExchangeModel;
import com.exrate.repository.ExRateRepository;
import com.exrate.repository.HistoricalExRateRepository;
import com.exrate.util.ExRateUtil;

@Service
public class ExRateServiceImpl implements ExRateService{
	
	private static final Logger logger = LoggerFactory.getLogger(ExRateServiceImpl.class);
	
	@Autowired
	CustomProperties applicationProperties;
	
	@Autowired
	ExRateRepository exRateRepository;
	
	@Autowired
	HistoricalExRateRepository historyRepository;
	
	@Override
	public ExchangeResponseDTO getExchangeRateResponse(String requestType, String date)
	{
		ExchangeResponseDTO exchangeResponseDTO = new ExchangeResponseDTO();
		String URL = getServiceURL(requestType, date);
		Set<String> supportedCurrencies = new HashSet<String>();
		Collections.addAll(supportedCurrencies, applicationProperties.getCurrencies().split(","));
		exchangeResponseDTO.setBase("USD");
		try{
			exchangeResponseDTO.setRecentQueriedDates(historyRepository.findRecentQueries());
			//Code for latest
			if(!"historical".equalsIgnoreCase(requestType))
			{
				exchangeResponseDTO.setExchangeRateDate(ExRateUtil.getDateAsString(new Date()));
				Set<ExchangeRates> exchangeRates = new HashSet<ExchangeRates>();
				exchangeRates.addAll(historyRepository.findbyExchangeRateDate(ExRateUtil.getDateAsString(new Date())));
				if(null != exchangeRates && !exchangeRates.isEmpty())
				{
					System.err.println("Queried local DB this time ...");
					exchangeResponseDTO.setSupportedCurrencyRatesList(ExRateUtil.getRatesDTOfromRatesEntity(exchangeRates));
					exchangeResponseDTO.setQueriedDate(ExRateUtil.getDateAsString(new Date()));
					return exchangeResponseDTO;
					
				} 
				else
				{
					System.err.println("Querying Open Exchange this time ...");
					OpenExchangeModel response = exRateRepository.getExchangeRate(URL);
					ExRateUtil.transferOpenExchangeModeltoDTO(response, exchangeResponseDTO, supportedCurrencies);
					//exchangeResponseDTO.setSupportedCurrencyRatesList(rates);
					exchangeResponseDTO.setQueriedDate(ExRateUtil.getDateAsString(new Date()));
					exchangeRates = ExRateUtil.getExchangeRateEntityfromDTO(exchangeResponseDTO.getSupportedCurrencyRatesList());
					exchangeRates.forEach(p -> p.setExchangeRateDate(ExRateUtil.getDateAsString(new Date())));
					historyRepository.save(exchangeRates).forEach(p -> System.err.println("Ex Currency : " + p.getToCurrency() + " Ex Rate date : " + p.getExchangeRateDate()));;
					return exchangeResponseDTO;
				}
			} 
			else if("historical".equalsIgnoreCase(requestType))
			{
				exchangeResponseDTO.setExchangeRateDate(date);
				Set<ExchangeRates> exchangeRates = new HashSet<ExchangeRates>();
				exchangeRates.addAll(historyRepository.findbyExchangeRateDate(date));
				if(null != exchangeRates && !exchangeRates.isEmpty())
				{
					System.err.println("Queried local DB this time ...");
					exchangeResponseDTO.setSupportedCurrencyRatesList(ExRateUtil.getRatesDTOfromRatesEntity(exchangeRates));
					exchangeResponseDTO.setQueriedDate(date);
					exchangeRates.stream().forEach(p -> p.setQueriedDate(new Date()));
					historyRepository.save(exchangeRates);
					return exchangeResponseDTO;
					
				} 
				else
				{
					System.err.println("Querying Open Exchange this time ..." + URL);
					OpenExchangeModel response = exRateRepository.getExchangeRate(URL);
					ExRateUtil.transferOpenExchangeModeltoDTO(response, exchangeResponseDTO, supportedCurrencies);
					exchangeResponseDTO.setQueriedDate(date);
					exchangeRates = ExRateUtil.getExchangeRateEntityfromDTO(exchangeResponseDTO.getSupportedCurrencyRatesList());
					exchangeRates.forEach(p ->
								p.setExchangeRateDate(date));
								//p.setExchangeRateDate(ExRateUtil.getDateAsString(new Date())));
					exchangeRates.forEach(p -> 
								p.setQueriedDate(new Date()));
					historyRepository.save(exchangeRates).forEach(p -> System.err.println("Ex Currency : " + p.getToCurrency() + " Ex Rate date : " + p.getExchangeRateDate()));;
					return exchangeResponseDTO;
				}
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			if(null != e  && null != exchangeResponseDTO && null != exchangeResponseDTO.getMessage() && !"".equals(exchangeResponseDTO.getMessage())) return exchangeResponseDTO;
			else if(null != exchangeResponseDTO && null != e)
			{
				exchangeResponseDTO.setMessage(e.getMessage());
			}
		}
		
			
		return exchangeResponseDTO;
	}
	
	public String getServiceURL(String requestType, String date)
	{
		StringBuilder url = new StringBuilder();
		if("latest".equalsIgnoreCase(requestType)){
			url.append(applicationProperties.getOpenexchangeUrl());
			url.append("?app_id=").append(applicationProperties.getAppId());
		} 
		else
		{
			url.append(applicationProperties.getHistoricOpenexchangeUrl());
			url.append(date).append(".json");
			url.append("?app_id=").append(applicationProperties.getAppId());
		}
		return url.toString();
	}
	
	
	

}
