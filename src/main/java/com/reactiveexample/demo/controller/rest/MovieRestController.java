package com.reactiveexample.demo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactiveexample.demo.dto.MovieDto;
import com.reactiveexample.demo.service.MovieService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("api/movies")
public class MovieRestController {

	@Autowired
	MovieService movieservice;	
	
	@GetMapping
	public Flux<MovieDto> getAllMovie(){
		log.info("Reactive GetAllMovie");
		return movieservice.findAllMovie();
	}
}