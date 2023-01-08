package com.reactiveexample.demo.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reactiveexample.demo.model.User;
import com.reactiveexample.demo.repository.UserRepository;
import com.reactiveexample.demo.security.JwtTokenProvider;



@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired 
  private AuthenticationManager authenticationManager;

  public String login(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    } catch (AuthenticationException e) {
    	e.printStackTrace();
      throw new RuntimeException("Invalid username/password supplied");
    }
  }

  public String register(User user) {
	  User another = this.userRepository.findByUsername(user.getUsername());
	  System.out.println("User another "+another);
	  if(another == null)
	  {
		  user.setPassword(passwordEncoder.encode(user.getPassword()));
	      userRepository.save(user);
	      return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
	  }
	  else
	  {
		  throw new RuntimeException("User name already exist");
	  }	  	  
  }

}
