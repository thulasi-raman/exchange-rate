package com.exrate.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.exrate.dto.RatesDTO;
import com.exrate.dto.ExchangeResponseDTO;
import com.exrate.entity.ExchangeRates;
import com.exrate.entity.OpenExchangeModel;

public class ExRateUtil {
	
	public static int getAge(Date dateOfBirth)
	{
		/*long ageInMillis = new Date().getTime() - dateOfBirth.getTime();
		Calendar calendar = GregorianCalendar.getInstance();
	    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18)
		return new Date(ageInMillis).Tel;*/
		return 0;
	}
	
	public static Set<RatesDTO> getSupportedCurrencies(Set<RatesDTO> totalRatesDTO, Set<String> supportedCurrencies)
	{
		Set<RatesDTO> supportCurrencyRates = new HashSet<RatesDTO>();
		for(RatesDTO rates:totalRatesDTO)
		{
			for(String currency:supportedCurrencies)
			{
				if(currency.equalsIgnoreCase(rates.getCurrency())) supportCurrencyRates.add(rates);
			}
		}
		return supportCurrencyRates;
	}
	
	public static void transferOpenExchangeModeltoDTO(OpenExchangeModel model, ExchangeResponseDTO dto, Set<String> supportedCurrencies)
	{
		dto.setBase(model.getBase());
		dto.setMessage(model.getMessage());
		dto.setStatus(model.getStatus());
		Set<RatesDTO> rates = new HashSet<RatesDTO>();
		for(String currency:supportedCurrencies)
		{
			RatesDTO rateDTO = new RatesDTO();
			rateDTO.setCurrency(currency);
			rateDTO.setExchangeRate(model.getRates().get(currency));
			rates.add(rateDTO);
		}
		dto.setSupportedCurrencyRatesList(rates);
	}
	
	public static String getDateAsString(Date date)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	public static Set<RatesDTO> getRatesDTOfromRatesEntity(Set<ExchangeRates> exchangeRates)
	{
		Set<RatesDTO> rates = exchangeRates.stream()
				.map(exchangeRatesToRatesDTO)
				.collect(Collectors.<RatesDTO> toSet());
		rates.forEach(p -> System.err.println("Rates Currency: " + p.getCurrency()));
		return rates;
	}
	
	
	private static Function<ExchangeRates, RatesDTO> exchangeRatesToRatesDTO = new Function<ExchangeRates, RatesDTO>() {
	    public RatesDTO apply(ExchangeRates t) {
	    	RatesDTO ratesDTO = new RatesDTO();
	    	ratesDTO.setCurrency(t.getToCurrency());
	    	ratesDTO.setExchangeRate(t.getValue());
	        return ratesDTO;
	    }
    };
    
    
    public static Set<ExchangeRates> getExchangeRateEntityfromDTO(Set<RatesDTO> rates)
    {
    	if(null != rates && !rates.isEmpty())
    	{
    		Set<ExchangeRates> exRates = new HashSet<ExchangeRates>();
    		for(RatesDTO rate:rates)
    		{
    			ExchangeRates exRate = new ExchangeRates();
    			exRate.setBaseCurrency("USD");
    			exRate.setToCurrency(rate.getCurrency());
    			exRate.setValue(rate.getExchangeRate());
    			exRate.setQueriedDate(new Date());
    			exRates.add(exRate);
    		}
    		return exRates;
    	}
    	return null;
    }
    
   
    
    

}
