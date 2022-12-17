package com.reactiveexample.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.reactiveexample.demo.service.MovieService;

import lombok.extern.slf4j.Slf4j;

import com.reactiveexample.demo.dto.MovieDetailDto;
import com.reactiveexample.demo.dto.MovieDto;
import com.reactiveexample.demo.model.Movie;
import com.reactiveexample.demo.repository.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public Flux<MovieDto> findAllMovie() {		
		
		
		Flux<MovieDto> movies= movieRepository.findAll().map(movie->modelMapper.map(movie, MovieDto.class));
		
		
		return movies;	
	}


	@Override
	public Mono<Movie> saveMovie(MovieDto movieDto) {
//	public Mono<MovieDto> saveMovie(MovieDto movieDto) {
		
		log.info("MovieDto : "+movieDto);
		Movie movie = modelMapper.map(movieDto, Movie.class);
		
		log.info("movie : "+movie);
		
		
		//MovieDto savedMovie=modelMapper.map(movieRepository.save(movie).subscribe(),MovieDto.class);
		Mono<Movie> savedMovie=movieRepository.save(movie);

		log.info("savedMovie : "+savedMovie);
		
		return savedMovie;
		/*savedMovie.subscribe();
		
		
		
		 MovieDto returnedMmovieDto=modelMapper.map(savedMovie,MovieDto.class);
		
		 log.info("returnedMmovieDto : "+returnedMmovieDto); 
		 return Mono.just(returnedMmovieDto);
		*/
	}
}