package com.example.morganstanley.service;

import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.morganstanley.dto.request.CurrencyConversionRequest;
import com.example.morganstanley.impl.CountryImpl;
import com.example.morganstanley.impl.CurrencyConversionRateImpl;
import com.example.morganstanley.model.Country;
import com.example.morganstanley.model.CurrencyConversionRate;

@Service
public class MorganStanleyService {
	
	@Autowired
	CurrencyConversionRateImpl currencyConversionRateImpl;
	
	@Autowired
	CountryImpl countryImpl;

	public String getChangedCurrencyAmount(@Valid CurrencyConversionRequest currencyRequest) {
		// TODO Auto-generated method stub
		System.out.println("Currency Request "+currencyRequest.getSourceCurrencyName());
		System.out.println("Currency Request "+currencyRequest.getTargetCurrencyName());
		
		Country source = countryImpl.findByCurrencyCode(currencyRequest.getSourceCurrencyName());
		Country target = countryImpl.findByCurrencyCode(currencyRequest.getTargetCurrencyName());
		
		if(source == null) {
			return currencyRequest.getSourceCurrencyName()+" Not Supported...";
		}
		
		if(target==null) {
			return currencyRequest.getTargetCurrencyName()+" Not Supported..";
		}
		
		if(source.getId().intValue() == target.getId().intValue()) {
			return String.valueOf(currencyRequest.getSourceCurrencyQuantity());
		}
		
		CurrencyConversionRate rate = currencyConversionRateImpl.findBySourceAndTarget(source, target);
		CurrencyConversionRate inversionRate = currencyConversionRateImpl.findBySourceAndTarget(target, source); // Checking for inversion rate present in the system. i.e source and target interchanged
		if(rate!=null) {
			System.out.println("Inside rate not null...");
			Integer sourceQuantity = currencyRequest.getSourceCurrencyQuantity();
			Double targetQuantity =  (sourceQuantity*rate.getConversionRate());
			Integer targetIntQuantity = (int) Math.round(targetQuantity);
			return String.valueOf(targetIntQuantity);
		}else if(inversionRate!=null){
			Integer sourceQuantity = currencyRequest.getSourceCurrencyQuantity();
			Double targetQuantity =  (sourceQuantity/inversionRate.getConversionRate());
			Integer targetIntQuantity = (int) Math.round(targetQuantity);
			return String.valueOf(targetIntQuantity);
		}
		else {
			HashSet<Integer> visited = new HashSet<Integer>();
			boolean present = false;
			visited.add(source.getId());
			Integer quantity = calculateQuantity(source,target,visited,present,currencyRequest.getSourceCurrencyQuantity());
			if(quantity==null) {
				return "There is no conversion for this currency..";
			}
			return String.valueOf(quantity);
		}
	}

	private Integer calculateQuantity(Country source, Country target, HashSet<Integer> visited, boolean present, Integer sourceQuantity) {
		// TODO Auto-generated method stub
		
		/*while(true) {*/
		    Integer quantity = null;
			CurrencyConversionRate rate = currencyConversionRateImpl.findBySourceAndTarget(source, target);
			CurrencyConversionRate inversionRate = currencyConversionRateImpl.findBySourceAndTarget(source, target); // Checking for inversion rate present in the system. i.e source and target interchanged
			if(rate!=null) {
				//Integer sourceQuantity = currencyRequest.getSourceCurrencyQuantity();
				Double targetQuantity =  (sourceQuantity*rate.getConversionRate());
				Integer targetIntQuantity = (int) Math.round(targetQuantity);
				return targetIntQuantity;
			}else if(inversionRate!=null){
				Double targetQuantity =  (sourceQuantity/inversionRate.getConversionRate());
				Integer targetIntQuantity = (int) Math.round(targetQuantity);
				return targetIntQuantity;
			}
			else {
				List<CurrencyConversionRate> sourceList = currencyConversionRateImpl.findBySource(source);
					for(CurrencyConversionRate currencyRate : sourceList) {
						if(visited.add(currencyRate.getTarget().getId())) {
							Double targetQuantity =  (sourceQuantity*currencyRate.getConversionRate());
							Integer targetIntQuantity = (int) Math.round(targetQuantity);
							quantity = calculateQuantity(currencyRate.getTarget(), target, visited, present,targetIntQuantity);
							if(quantity!=null) {
								break;
							}
						}
					}
				if(quantity == null) {
					List<CurrencyConversionRate> targetList = currencyConversionRateImpl.findByTarget(source);
					for(CurrencyConversionRate currencyRate : targetList) {
						if(visited.add(currencyRate.getSource().getId())) {
							Double targetQuantity =  (sourceQuantity/currencyRate.getConversionRate());
							Integer targetIntQuantity = (int) Math.round(targetQuantity);
							quantity = calculateQuantity(currencyRate.getSource(), target, visited, present,targetIntQuantity);
							if(quantity!=null) {
								break;
							}
						}
					}
				}
			}
		return quantity;
	}

}
