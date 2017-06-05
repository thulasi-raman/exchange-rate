package com.exrate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "ExchangeRates.findbyExchangeRateDate", query = "SELECT ex FROM ExchangeRates ex WHERE LOWER(ex.exchangeRateDate) = LOWER(?1)")
public class ExchangeRates {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(nullable=false)
	private String baseCurrency;
	
	@Column(nullable=false)
	private String toCurrency;
	
	@Column(nullable=false)
	private String exchangeRateDate;
	
	@Column(nullable=false)
	private double value;
	
	@Column(nullable=false)
	private Date queriedDate;
	
	
	
	public Date getQueriedDate() {
		return queriedDate;
	}

	public void setQueriedDate(Date queriedDate) {
		this.queriedDate = queriedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public String getExchangeRateDate() {
		return exchangeRateDate;
	}

	public void setExchangeRateDate(String exchangeRateDate) {
		this.exchangeRateDate = exchangeRateDate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}



}
