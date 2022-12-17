package com.reactiveexample.demo.controller.rest;

import com.reactiveexample.demo.model.Movie;
import com.reactiveexample.demo.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactiveexample.demo.dto.MovieDto;
import com.reactiveexample.demo.service.MovieService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("api/movies")
public class MovieRestController {

	@Autowired
	MovieRepository movieDao;
	
	@Autowired
	MovieService movieservice;	
	
	@GetMapping
	public Flux<MovieDto> getAllMovie(){
		log.info("Reactive GetAllMovie");
		return movieservice.findAllMovie();
	}
	
	
	@PostMapping
	public Mono<Movie> createMovie(@Valid @RequestBody MovieDto movieDto){
		log.info("Reactive Save Movie");
		return movieservice.saveMovie(movieDto);
		
	}
	
	
	/*@PostMapping
	public Mono<Movie> createMovie(@Valid @RequestBody Movie movie){
		
		log.info("Reactive Save Movie");
		return movieDao.save(movie);
		
	}*/
}