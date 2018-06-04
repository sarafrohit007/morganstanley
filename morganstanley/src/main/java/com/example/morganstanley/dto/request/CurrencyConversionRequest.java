package com.example.morganstanley.dto.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CurrencyConversionRequest {

	private String sourceCurrencyName;
	
	private Integer sourceCurrencyQuantity;
	
	private String targetCurrencyName;

	public String getSourceCurrencyName() {
		return sourceCurrencyName;
	}

	public void setSourceCurrencyName(String sourceCurrencyName) {
		this.sourceCurrencyName = sourceCurrencyName;
	}

	public Integer getSourceCurrencyQuantity() {
		return sourceCurrencyQuantity;
	}

	public void setSourceCurrencyQuantity(Integer sourceCurrencyQuantity) {
		this.sourceCurrencyQuantity = sourceCurrencyQuantity;
	}

	public String getTargetCurrencyName() {
		return targetCurrencyName;
	}

	public void setTargetCurrencyName(String targetCurrencyName) {
		this.targetCurrencyName = targetCurrencyName;
	}
	
	
}
