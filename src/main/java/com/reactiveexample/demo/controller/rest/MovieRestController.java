package com.reactiveexample.demo.controller.rest;

import com.reactiveexample.demo.model.Movie;
import com.reactiveexample.demo.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@CrossOrigin
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Movie> streamMovie() {
        return movieDao.findAll();
    }
	
	
	/* @GetMapping("/{id}")	 
	public Mono<ResponseEntity<Movie>> 
		getMovieById(@PathVariable(value = "id") String movieId) {
	        return movieDao.findById(movieId)
	                .map(savedMovie -> ResponseEntity.ok(savedMovie))
	                .defaultIfEmpty(ResponseEntity.notFound().build());
	    }
*/
	
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<MovieDto>> 
		getMovieById(@PathVariable(value = "id") String movieId) {
	        return movieservice.findOneMovie(movieId)
	                .map(savedMovie -> ResponseEntity.ok(savedMovie))
	                .defaultIfEmpty(ResponseEntity.notFound().build());
	}

	
	
	@PostMapping
	public Mono<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto){
		log.info("Reactive Save Movie");
		return movieservice.saveMovie(movieDto);
		
	}
	
	
	
	//အောက်ကကောင်က MovieRepository ကို တိုက်ရိုက်ခေါ်သုံးထားတာ
	
	/*@PostMapping
	public Mono<Movie> createMovie(@Valid @RequestBody Movie movie){
		
		log.info("Reactive Save Movie");
		return movieDao.save(movie);
		
	}
	*/
	
	
	
}