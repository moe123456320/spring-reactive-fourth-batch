package com.reactiveexample.demo.controller.rest;

import com.reactiveexample.demo.model.Movie;
import com.reactiveexample.demo.repository.MovieDetailRepository;
import com.reactiveexample.demo.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	MovieDetailRepository movieDetailDao;
	
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

	
	
	@GetMapping("/name")
	public Flux<Movie> getMovieByName(@RequestParam String movieName){
		System.out.println("Movie name :"+movieName);
		
		Movie movie=new Movie();
		movie.setName(movieName);
		
		//ExampleMatcher matcher=ExampleMatcher.matchingAny();
		Example<Movie>  example=Example.of(movie);
		
		return movieDao.findAll(example);		
	}
	
	@GetMapping("/director")
	public Flux<Movie> getMovieByDirector(@RequestParam String director){
		
			log.info(" \n Movie Director  : "+director);
			//return movieDao.findByDirector(director);
			return movieDao.findByDirectorUsingCustomQuery(director);
	}
	
	/**********************************************************************/
	@PostMapping
	public Mono<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto){
		log.info("Reactive Save Movie");
		return movieservice.saveMovie(movieDto);
		
	}
	
	
	
	//အောက်ကကောင်က MovieRepository ကို တိုက်ရိုက်ခေါ်သုံးထားတာ	
	/*@PostMapping
	public Mono<Movie> createMovie(@Valid @RequestBody Movie movie){
	if(movie.getDetails()!=null) {
		return this.movieDetailDao.save(movie.getDetails())
				.flatMap((movieDetails)->{
					movie.setDetails(movieDetails);
					return this.movieDao.save(movie);
					
				});
	}
		log.info("Reactive Save Movie");
		return movieDao.save(movie);
		
	}
	*/
	
	@PutMapping("/{id}")
    public Mono<ResponseEntity<MovieDto>> updateMovie(@PathVariable(value = "id") String movieId,
                                                   @Valid @RequestBody MovieDto movieDto) {
		
		return movieservice.editMovie(movieDto, movieId)
                .map(updatedMovie -> new ResponseEntity<>(updatedMovie, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                
    }
	
	
//အောက်ကကောင်က MovieRepository ကို တိုက်ရိုက်ခေါ်သုံးထားတာ	
/*	@PutMapping("/{id}")
    public Mono<ResponseEntity<Movie>> updateMovie(@PathVariable(value = "id") String movieId,
                                                   @Valid @RequestBody Movie movie) {		
		return movieDao.findById(movieId)
                .flatMap(existingMovie -> {
                    existingMovie.setName(movie.getName());
                    existingMovie.setDirector(movie.getDirector());
                    existingMovie.setYear(movie.getYear());
                    return movieDao.save(existingMovie);
                })
                .map(updatedMovie -> new ResponseEntity<>(updatedMovie, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
*/

/*	
@DeleteMapping("/{id}")
public Mono<ResponseEntity<Void>> deleteMovie(@PathVariable(value = "id")String movieId){
	return movieservice.deleteMovie(movieId)
			.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))  
		.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND)); 
	
}
*/	
		

	@DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteMovie(@PathVariable(value = "id") String movieId) {
        return movieDao.findById(movieId)
        		
                .flatMap(existingMovie ->{                            
                	if(existingMovie.getDetails()!=null) {
                		log.info("MovieDetail:"+existingMovie.getDetails());
                		movieDetailDao.delete(existingMovie.getDetails()).subscribe();
                	}                	
                	return	movieDao.delete(existingMovie)                	
                      .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
                	 
                })
                
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}