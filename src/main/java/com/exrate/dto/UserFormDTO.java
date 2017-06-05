package com.exrate.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

public class UserFormDTO {

	@NotNull(message="E Mail is required")
	@Email(message = "Enter valid E mail ID")
	private String email;
	
	@NotNull(message = "Password is required")
    @Size(min = 6, message = "Password length must be minimum 6 Charecters long")
	private String password;
	
	@NotNull(message = "Password is required")
    @Size(min = 6, message = "Password length must be minimum 6 Charecters long")
	private String repeatPassword;
	
	@NotNull(message = "Date of birth is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private Date dateOfBirth;
	
	@NotNull(message = "Address is required")
    @Size(min = 1, message = "Address is required")
	private String address;
	
	@NotNull(message = "Zip code is required")
    @Size(min = 5, message = "Zip code is required")
	private String zip;
	
	@NotNull(message = "Country is required")
    @Size(min = 5, message = "Country is required")
	private String country;
	
	
	@NotNull(message = "Name is required")
    @Size(min = 5, message = "Name is required")
	private String name;
	
	@NotNull(message = "City is required")
    @Size(min = 4, message = "City is required")
	private String city;
	
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	

}
