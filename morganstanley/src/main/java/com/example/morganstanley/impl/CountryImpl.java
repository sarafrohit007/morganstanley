package com.example.morganstanley.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.example.morganstanley.model.Country;
import com.example.morganstanley.repository.CountryRepositoryCustom;

@Component
public class CountryImpl implements CountryRepositoryCustom{
	
	@PersistenceContext
	protected EntityManager em;

	@Override
	public Country findByCurrencyCode(String currencyCode) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Country> criteria = builder.createQuery( Country.class );
		Root<Country> root = criteria.from( Country.class );
		criteria.select( root );
		criteria.where( builder.equal( root.get( "currencyCode" ), currencyCode ) );
		try {
			return (Country)em.createQuery( criteria ).getSingleResult();
		}catch(Exception e) {
			return null;
		}
	}

}
