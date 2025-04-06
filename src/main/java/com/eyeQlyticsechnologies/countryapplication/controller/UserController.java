package com.eyeQlyticsechnologies.countryapplication.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyeQlyticsechnologies.countryapplication.config.JwtUtil;
import com.eyeQlyticsechnologies.countryapplication.model.Users;
import com.eyeQlyticsechnologies.countryapplication.repository.response.UserResponse;
import com.eyeQlyticsechnologies.countryapplication.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> registerAUser(@RequestBody Users user) {
		Users pojo = userService.createUser(user);
		if (pojo != null) {
			return new ResponseEntity<UserResponse>(new UserResponse("OK", "User created successfully", pojo, null),
					HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(new UserResponse("Fail", "User not created", null, null),
				HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Users user) {
		Optional<Users> dbUser = userService.getUserByEmail(user.getEmail());
		if (dbUser.isPresent() && dbUser.get().getEmail().equals(user.getEmail())
				&& passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) { // Ensure password matches

			try {
				return ResponseEntity.ok("Token : " + jwtUtil.generateToken(user.getEmail()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.status(401).body("Invalid credentials");
	}

	@GetMapping("/getById/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable int userId) {
		Users pojo = userService.getUserById(userId);
		if (pojo != null) {
			return new ResponseEntity<UserResponse>(new UserResponse("Ok", "User found successfully", pojo, null),
					HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(new UserResponse("Fail", "User not found", null, null),
				HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable int userId) {
		Users pojo = userService.getUserById(userId);
		if (pojo != null) {
			userService.deleteUser(pojo.getUserId());
			return new ResponseEntity<UserResponse>(new UserResponse("OK", "User deleted successfully", null, null),
					HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(new UserResponse("Fail", "User not found", null, null),
				HttpStatus.NOT_FOUND);

	}

	@PatchMapping("/updateUser/{userId}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable int userId, @RequestBody Users user) {
		Users pojo = userService.updateUser(userId, user);
		if (pojo != null) {
			return new ResponseEntity<UserResponse>(new UserResponse("Ok", "User updated successfully", pojo, null),
					HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(new UserResponse("Fail", "User not updated", pojo, null),
				HttpStatus.BAD_REQUEST);
	}

}
