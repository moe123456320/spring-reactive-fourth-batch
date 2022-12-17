package com.reactiveexample.demo.service;

import com.reactiveexample.demo.dto.MovieDto;
import com.reactiveexample.demo.model.Movie;
import reactor.core.publisher.Flux;

public interface MovieService {

	public Flux<MovieDto> findAllMovie();
}