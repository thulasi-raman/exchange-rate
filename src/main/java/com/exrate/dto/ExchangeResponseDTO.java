package com.exrate.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class ExchangeResponseDTO {
	
	private String message;
	private String base;
	private long timeStamp;
	private int status;
	private boolean error;
	private String queriedDate;
	//@DateTimeFormat(pattern="dd-MM-yyyy")
	private String  exchangeRateDate;
	
	private List<String> recentQueriedDates;
	
	private Set<RatesDTO> supportedCurrencyRatesList;
	
	
	
	public List<String> getRecentQueriedDates() {
		return recentQueriedDates;
	}

	public void setRecentQueriedDates(List<String> recentQueriedDates) {
		this.recentQueriedDates = recentQueriedDates;
	}

	public String getExchangeRateDate() {
		return exchangeRateDate;
	}

	public void setExchangeRateDate(String exchangeRateDate) {
		this.exchangeRateDate = exchangeRateDate;
	}

	public Set<RatesDTO> getSupportedCurrencyRatesList() {
		return supportedCurrencyRatesList;
	}

	public void setSupportedCurrencyRatesList(Set<RatesDTO> supportedCurrencyRatesList) {
		this.supportedCurrencyRatesList = supportedCurrencyRatesList;
	}


	public String getQueriedDate() {
		return queriedDate;
	}

	public void setQueriedDate(String queriedDate) {
		this.queriedDate = queriedDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	

}
