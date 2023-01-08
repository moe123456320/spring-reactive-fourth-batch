package com.reactiveexample.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.reactiveexample.demo.service.MovieService;

import lombok.extern.slf4j.Slf4j;

import com.reactiveexample.demo.dto.MovieDetailDto;
import com.reactiveexample.demo.dto.MovieDto;
import com.reactiveexample.demo.model.Movie;
import com.reactiveexample.demo.model.MovieDetail;
import com.reactiveexample.demo.repository.MovieDetailRepository;
import com.reactiveexample.demo.repository.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieDetailRepository movieDetailRepository;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public Flux<MovieDto> findAllMovie() {		
		
		Flux<MovieDto> movies= movieRepository.findAll().
							map(movie->modelMapper.map(movie, MovieDto.class));
		return movies;	
	}


		@Override
		public Mono<MovieDto> saveMovie(MovieDto movieDto) {
		//asynchronous ( non-blocking io )	
		if(movieDto.getDetails()!=null) {					
			 return this.movieDetailRepository.save(modelMapper.map(movieDto.getDetails(),MovieDetail.class))
					.flatMap((movieDetail)-> {
						movieDto.setDetails(modelMapper.map(movieDetail,MovieDetailDto.class));
			return this.movieRepository.save(modelMapper.map(movieDto, Movie.class))
					.map(movie->modelMapper.map(movie, MovieDto.class));			
			});		
		}
			
		return movieRepository.save(modelMapper.map(movieDto, Movie.class))
				.map(movie->modelMapper.map(movie, MovieDto.class));
			
			//အောက် ကကောင်က synchronous( blocking  i/o) 
			/*
			Movie movie = modelMapper.map(movieDto, Movie.class);		
			Mono<Movie> savedMovie=movieRepository.save(movie);				
			MovieDto movieDto1=savedMovie.map(movie1->modelMapper.map(movie1, MovieDto.class)).block();				
			return Mono.just(movieDto1);
			*/
		}
		

	@Override
	public Mono<MovieDto> findOneMovie(String id) {
		//‌Asynchronous and non-blocking
		return movieRepository.findById(id)
				.map(movie->modelMapper.map(movie, MovieDto.class));

		//‌synchronous and blocking
		/*Mono<Movie> movie=movieRepository.findById(id);		
		MovieDto movieDto=movie.map(movie1->modelMapper.map(movie1, MovieDto.class)).block();
		//log.info("MovieDto:"+movieDto);		
		return Mono.just(movieDto);*/
					
	}


	@Override
	public Mono<MovieDto> editMovie(MovieDto movieDto,String id) {
		
		return movieRepository.findById(id)//return Movie
					.flatMap(existingMovie -> {
						existingMovie.setName(movieDto.getName());
						existingMovie.setDirector(movieDto.getDirector());
						existingMovie.setYear(movieDto.getYear());
					
					  if(movieDto.getDetails()!=null) {
					  
					  return this.movieDetailRepository.save(modelMapper.map(movieDto.getDetails(),MovieDetail.class))
							  	.flatMap((movieDetail)-> {
							  			existingMovie.setDetails(movieDetail);			
							  				return this.movieRepository.save(existingMovie)
							  							.map(movie->modelMapper.map(movie, MovieDto.class));
								
							  		});
					  	}
					 			
						return this.movieRepository.save(existingMovie); 
        }).map(movie->modelMapper.map(movie, MovieDto.class));	
	}
}

