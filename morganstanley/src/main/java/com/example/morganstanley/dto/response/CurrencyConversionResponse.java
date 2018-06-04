package com.example.morganstanley.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CurrencyConversionResponse {

	private Integer targetCurrencyQuantity;

	public Integer getTargetCurrencyQuantity() {
		return targetCurrencyQuantity;
	}

	public void setTargetCurrencyQuantity(Integer targetCurrencyQuantity) {
		this.targetCurrencyQuantity = targetCurrencyQuantity;
	}
	
	
}
