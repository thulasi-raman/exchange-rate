package com.exrate.validator;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.exrate.dto.ExchangeResponseDTO;

@Component
public class HomeValidator implements Validator{
	
	@Override
    public boolean supports(Class<?> clazz) {
        return ExchangeResponseDTO.class.isAssignableFrom(clazz);
    }
	
	@Override
    public void validate(Object target, Errors errors) {
		ExchangeResponseDTO responseDTO = (ExchangeResponseDTO) target;
        validateQueryDate(responseDTO, errors);
        
    }
	
	public void validateQueryDate(ExchangeResponseDTO responseDTO, Errors errors)
	{
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(responseDTO.getExchangeRateDate());
			if(date.after(new Date())) errors.rejectValue("exchangeRateDate", "exchangeRateDate", "Future date could not be queried!! ");
		} catch(Exception e)
		{
			errors.rejectValue("exchangeRateDate", "exchangeRateDate", "Date format not supported");
		}
	}
	

}
