package com.reactiveexample.demo.controller.rest;

//import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactiveexample.demo.dto.Token;
import com.reactiveexample.demo.model.User;
import com.reactiveexample.demo.service.UserService;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
	  @Autowired
	  private UserService userService;

	  @PostMapping("/login")
	  public Token login(@Valid @RequestBody User user) {
		Token token = new Token();
		String tok = userService.login(user.getUsername(), user.getPassword());
		token.setToken(tok);
	    return token;
	  }
	  
	  
//	  @PreAuthorize("hasRole('ADMIN')")
	  @PostMapping("/register")
	  public String register(@Valid @RequestBody User user) {
		log.info("User Controller"+user);  
	    return userService.register(user);
	  }
}
