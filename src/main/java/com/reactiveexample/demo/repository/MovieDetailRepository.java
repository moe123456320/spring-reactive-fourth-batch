package com.reactiveexample.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactiveexample.demo.model.MovieDetail;



@Repository
public interface MovieDetailRepository extends ReactiveMongoRepository<MovieDetail, String>{

}
	