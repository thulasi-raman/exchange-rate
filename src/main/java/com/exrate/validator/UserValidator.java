package com.exrate.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.exrate.dto.UserFormDTO;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator{
	
	@Override
    public boolean supports(Class<?> clazz) {
        return UserFormDTO.class.isAssignableFrom(clazz);
    }
	
	@Override
    public void validate(Object target, Errors errors) {
		UserFormDTO userForm = (UserFormDTO) target;
        validatePasswordRepeat(userForm, errors);
        validateBirthDate(userForm, errors);
    }
	
	public void validatePasswordRepeat(UserFormDTO userForm, Errors errors)
	{
		if(!userForm.getPassword().equals(userForm.getRepeatPassword()))
		errors.rejectValue("repeatPassword", "repeatpassword", "Password and repeated password does not match");
	}
	
	public void validateBirthDate(UserFormDTO userForm, Errors errors)
	{
		LocalDate date = userForm.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period period = Period.between(date, LocalDate.now());
		if( period.getYears() < 18 && period.getYears() > 120)
			errors.rejectValue("dateOfBirth", "dateOfBirth", "Age must be between 18 and 120 !!");
	}
	
	
	
	

}
