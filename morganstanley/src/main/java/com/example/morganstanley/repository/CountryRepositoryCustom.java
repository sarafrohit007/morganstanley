package com.example.morganstanley.repository;

import org.springframework.stereotype.Repository;

import com.example.morganstanley.model.Country;



@Repository
public interface CountryRepositoryCustom {
	
	Country findByCurrencyCode(String currencyCode);

}
