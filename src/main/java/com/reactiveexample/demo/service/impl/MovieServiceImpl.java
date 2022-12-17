package com.reactiveexample.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.reactiveexample.demo.repository.MovieRepository;
import com.reactiveexample.demo.service.MovieService;
import com.reactiveexample.demo.dto.MovieDetailDto;
import com.reactiveexample.demo.dto.MovieDto;

import reactor.core.publisher.Flux;

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
}