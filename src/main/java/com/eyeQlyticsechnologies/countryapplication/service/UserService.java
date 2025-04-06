package com.eyeQlyticsechnologies.countryapplication.service;

import java.util.Optional;

import com.eyeQlyticsechnologies.countryapplication.model.Users;

public interface UserService {

	Users createUser(Users user);

	Users getUserById(int userId);

	Optional<Users> getUserByEmail(String email);

	void deleteUser(int userId);

	Users updateUser(int userId, Users user);
	
}
