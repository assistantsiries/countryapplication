package com.eyeQlyticsechnologies.countryapplication.repository.response;

import java.util.List;

import com.eyeQlyticsechnologies.countryapplication.model.Users;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	private String status;
	private String msg;
	private Users data;
	private List<Users> list;

}
