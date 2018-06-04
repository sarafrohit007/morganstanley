package com.example.morganstanley.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.example.morganstanley.model.Country;
import com.example.morganstanley.model.CurrencyConversionRate;
import com.example.morganstanley.repository.CurrencyConversionRateRepositoryCustom;

@Component
public class CurrencyConversionRateImpl implements CurrencyConversionRateRepositoryCustom{
	
	@PersistenceContext
	protected EntityManager em;

	@Override
	public CurrencyConversionRate findBySourceAndTarget(Country source, Country target) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<CurrencyConversionRate> criteria = builder.createQuery( CurrencyConversionRate.class );
		Root<CurrencyConversionRate> root = criteria.from( CurrencyConversionRate.class );
		criteria.select( root );
		criteria.where(builder.and(builder.equal(root.get("source"), source), builder.equal(root.get("target"), target)));
		try {
			return (CurrencyConversionRate)em.createQuery( criteria ).getSingleResult();
		}catch(Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CurrencyConversionRate> findBySource(Country source) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<CurrencyConversionRate> criteria = builder.createQuery( CurrencyConversionRate.class );
		Root<CurrencyConversionRate> root = criteria.from( CurrencyConversionRate.class );
		criteria.select( root );
		criteria.where(builder.and(builder.equal(root.get("source"), source)));
		try {
			return (List<CurrencyConversionRate>)em.createQuery( criteria ).getResultList();
		}catch(Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CurrencyConversionRate> findByTarget(Country target) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<CurrencyConversionRate> criteria = builder.createQuery( CurrencyConversionRate.class );
		Root<CurrencyConversionRate> root = criteria.from( CurrencyConversionRate.class );
		criteria.select( root );
		criteria.where(builder.and(builder.equal(root.get("target"), target)));
		try {
			return (List<CurrencyConversionRate>)em.createQuery( criteria ).getResultList();
		}catch(Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

}
