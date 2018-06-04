package com.example.morganstanley.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.morganstanley.model.CurrencyConversionRate;


@Repository
public interface CurrencyConversionRateRepository extends JpaRepository<CurrencyConversionRate,Integer>,CurrencyConversionRateRepositoryCustom{

}
