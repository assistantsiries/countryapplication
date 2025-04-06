package com.eyeQlyticsechnologies.countryapplication.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeQlyticsechnologies.countryapplication.exception.ResourceNotFoundException;
import com.eyeQlyticsechnologies.countryapplication.model.Country;
import com.eyeQlyticsechnologies.countryapplication.repository.CountryRepository;
import com.eyeQlyticsechnologies.countryapplication.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public Country addCountry(Country country) {
        Country pojo=countryRepository.save(country);
		return pojo;
	}

	@Override
	public Country getCountryById(int id) {
	   Country pojo=countryRepository.findById(id).orElse(null);
		return pojo;
	}

	@Override
	public List<Country> getAllCountries() {
	 List<Country> countries=countryRepository.findAll();
		return countries;
	}

	@Override
	public void deleteCountry(int id) {
		countryRepository.deleteById(id);
		
	}

	@Override
	public Country updateCountry(int id,Country country) {
		Country existingData = countryRepository.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Country data not found with id: " + id));

		 if(country.getName() != null) {
			 existingData.setName(country.getName());
		 }
		if(country.getCode() != null) {
			existingData.setCode(country.getCode());
		}
		 countryRepository.save(existingData);
		 return existingData;
		
	
	}
	

}
