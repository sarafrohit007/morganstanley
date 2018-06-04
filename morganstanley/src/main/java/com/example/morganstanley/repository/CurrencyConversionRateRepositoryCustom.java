package com.example.morganstanley.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.morganstanley.model.Country;
import com.example.morganstanley.model.CurrencyConversionRate;

@Repository
public interface CurrencyConversionRateRepositoryCustom {

	CurrencyConversionRate findBySourceAndTarget(Country source,Country target);
	
	List<CurrencyConversionRate> findBySource(Country source);
	
	List<CurrencyConversionRate> findByTarget(Country target);
}
