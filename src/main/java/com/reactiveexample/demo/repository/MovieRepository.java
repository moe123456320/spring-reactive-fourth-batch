package com.reactiveexample.demo.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactiveexample.demo.model.Movie;

import reactor.core.publisher.Flux;


@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

	Flux<Movie> findByDirector(String director);
	
	@Query("{'director':?0}")//locational parameter
	Flux<Movie> findByDirectorUsingCustomQuery(String director);
	
	
}
