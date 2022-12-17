package com.reactiveexample.demo.dto;

import java.util.List;

import com.reactiveexample.demo.model.Actor;
import com.reactiveexample.demo.model.MovieDetail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;

//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MovieDto extends BaseDto {

	@NotBlank
    @Size(max = 140)
    private String name;
    
    @NotNull
    private Integer year;

    @NotNull
    private String director;
    
    
    private List<Actor> actors;

	
    MovieDetail details;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public List<Actor> getActors() {
		return actors;
	}


	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}


	public MovieDetail getDetails() {
		return details;
	}


	public void setDetails(MovieDetail details) {
		this.details = details;
	}

    
    
    
    
 } 