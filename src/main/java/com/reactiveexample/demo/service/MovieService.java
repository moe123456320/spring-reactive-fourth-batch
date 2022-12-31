package com.reactiveexample.demo.service;

import com.reactiveexample.demo.dto.MovieDto;
import com.reactiveexample.demo.model.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

	public Flux<MovieDto> findAllMovie();
	public Mono<MovieDto> saveMovie(MovieDto movieDto);
	public Mono<MovieDto> findOneMovie(String id);
	public Mono<MovieDto> editMovie(MovieDto movieDto,String id);
		
}