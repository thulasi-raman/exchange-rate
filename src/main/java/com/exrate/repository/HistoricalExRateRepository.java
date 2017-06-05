package com.exrate.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.exrate.entity.ExchangeRates;

public interface HistoricalExRateRepository  extends CrudRepository<ExchangeRates, Long>{
	
	
	public List<ExchangeRates> save(List<ExchangeRates> exchangeRates);
	
	public List<ExchangeRates> findbyExchangeRateDate(String exchangeRateDate);
	
	@Query("SELECT DISTINCT ex.exchangeRateDate FROM ExchangeRates ex")
	public List<String> findRecentQueries();
	
	

}
