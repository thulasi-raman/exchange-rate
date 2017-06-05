package com.exrate.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="currency.converter")
public class CustomProperties {
	
	private String appId;
	private String openexchangeUrl;
	private String historicOpenexchangeUrl;
	private String currencies;
	
	public String getCurrencies() {
		return currencies;
	}

	public void setCurrencies(String currencies) {
		this.currencies = currencies;
	}

	public String getHistoricOpenexchangeUrl() {
		return historicOpenexchangeUrl;
	}
	
	public void setHistoricOpenexchangeUrl(String historicOpenexchangeUrl) {
		this.historicOpenexchangeUrl = historicOpenexchangeUrl;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOpenexchangeUrl() {
		return openexchangeUrl;
	}
	public void setOpenexchangeUrl(String openexchangeUrl) {
		this.openexchangeUrl = openexchangeUrl;
	}
	
	

}
