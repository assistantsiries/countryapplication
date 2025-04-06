package com.eyeQlyticsechnologies.countryapplication.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eyeQlyticsechnologies.countryapplication.exception.ResourceNotFoundException;
import com.eyeQlyticsechnologies.countryapplication.model.Users;
import com.eyeQlyticsechnologies.countryapplication.repository.UserRepository;
import com.eyeQlyticsechnologies.countryapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Users createUser(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	    Users pojo=userRepository.save(user);
		return pojo;
	}

	@Override
	public Users getUserById(int userId) {
		Users pojo=userRepository.findById(userId).orElse(null);
		return pojo;
	}

	@Override
	public Optional<Users> getUserByEmail(String email) {
		Optional<Users> user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public Users updateUser(int userId,Users user) {
		Users existingUser = userRepository.findById(userId)
		        .orElseThrow(() -> new ResourceNotFoundException("User data not found with id: " + userId));

	    if(user.getName() != null) {
	    	existingUser.setName(user.getName());
	    }
	    if(user.getEmail()!= null) {
	    	existingUser.setEmail(user.getEmail());
	    }
	    if(user.getPassword()!=null) {
	    	existingUser.setPassword(user.getPassword());
	    }
	    userRepository.save(existingUser);

		return existingUser;
	}

}
