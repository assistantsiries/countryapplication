package com.eyeQlyticsechnologies.countryapplication.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eyeQlyticsechnologies.countryapplication.config.CustomUserDetails;
import com.eyeQlyticsechnologies.countryapplication.model.Users;
import com.eyeQlyticsechnologies.countryapplication.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(email);

        return user.map(userObj -> new CustomUserDetails(userObj))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        }

}
