package com.eyeQlyticsechnologies.countryapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyeQlyticsechnologies.countryapplication.model.Country;
import com.eyeQlyticsechnologies.countryapplication.repository.response.CountryResponse;
import com.eyeQlyticsechnologies.countryapplication.service.CountryService;


@RestController
@RequestMapping("/api/country")
public class CountryController {
	
	@Autowired
    private CountryService countryService;
	
	@PostMapping("/create")
	public ResponseEntity<CountryResponse> addCountry(@RequestBody Country country){
		Country pojo=countryService.addCountry(country);
		if(pojo != null) {
			return new ResponseEntity<CountryResponse>(new CountryResponse("OK", "Country added successfully", pojo, null), HttpStatus.OK);
		}
		return new ResponseEntity<CountryResponse>(new CountryResponse("Fail", "Country not added", null, null),HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<CountryResponse> getCountryById(@PathVariable int id){
		Country pojo=countryService.getCountryById(id);
		if(pojo != null) {
			return new ResponseEntity<CountryResponse>(new CountryResponse("OK", "Country found successfully", pojo, null),HttpStatus.OK);
		}
		return new ResponseEntity<CountryResponse>(new CountryResponse("Fail", "Country not found", null, null),HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<CountryResponse> getAllCountries(){
		List<Country> countries=countryService.getAllCountries();
		if(countries != null) {
			return new ResponseEntity<CountryResponse>(new CountryResponse("OK", "Countries found successfully", null, countries), HttpStatus.OK);
		}
		return new ResponseEntity<CountryResponse>(new CountryResponse("Fail", "No countries found", null, countries), HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CountryResponse> deleteCountry(@PathVariable int id){
		Country pojo=countryService.getCountryById(id);
		if(pojo != null) {
			countryService.deleteCountry(pojo.getId());
			return new ResponseEntity<CountryResponse>(new CountryResponse("OK", "Country deleted successfully", null, null),HttpStatus.OK);
		}
		return new ResponseEntity<CountryResponse>(new CountryResponse("Fail", "Country data not Found", null, null),HttpStatus.NOT_FOUND);
	
	}
	
	@PatchMapping("/updateCountry/{id}")
	public ResponseEntity<CountryResponse> updateCountry(@PathVariable int id, @RequestBody Country country){
		Country pojo=countryService.updateCountry(id,country);
		if(pojo != null) {
			return new ResponseEntity<CountryResponse>(new CountryResponse("OK", "Country data updated successfully", pojo, null), HttpStatus.OK);
		}
		return new ResponseEntity<CountryResponse>(new CountryResponse("Fail", "Country data not found", null, null), HttpStatus.NOT_FOUND);

	}

}
