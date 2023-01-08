package com.reactiveexample.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reactiveexample.demo.model.User;
import com.reactiveexample.demo.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	  //my own User
	  final User user = userRepository.findByUsername(username); 
	  System.out.println("Find by username "+username +" Got user "+user);
	  if (user == null) {
	      throw new UsernameNotFoundException("User '" + username + "' not found");
	    }

	  //Spring security's User
	  return org.springframework.security.core.userdetails.User//create UserDetails
		        .withUsername(username)//
		        .password(user.getPassword())//
		        .authorities(user.getRoles())//
		        .accountExpired(false)//
		        .accountLocked(false)//
		        .credentialsExpired(false)//
		        .disabled(false)//
		        .build();
  	}
}
