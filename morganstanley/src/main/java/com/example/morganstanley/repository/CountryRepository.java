package com.example.morganstanley.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.morganstanley.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer>,CountryRepositoryCustom{

}
