package com.eyeQlyticsechnologies.countryapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eyeQlyticsechnologies.countryapplication.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
	

}
