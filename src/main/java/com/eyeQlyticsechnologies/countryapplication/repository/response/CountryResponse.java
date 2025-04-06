package com.eyeQlyticsechnologies.countryapplication.repository.response;

import java.util.List;

import com.eyeQlyticsechnologies.countryapplication.model.Country;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryResponse {
	private String status;
	private String msg;
	private Country data;
	private List<Country> list;

}
