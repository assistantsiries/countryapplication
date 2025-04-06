package com.eyeQlyticsechnologies.countryapplication.service;

import java.util.List;

import com.eyeQlyticsechnologies.countryapplication.model.Country;

public interface CountryService {

	Country addCountry(Country country);

	Country getCountryById(int id);

	List<Country> getAllCountries();

	void deleteCountry(int id);

	Country updateCountry(int id,Country country);

}
