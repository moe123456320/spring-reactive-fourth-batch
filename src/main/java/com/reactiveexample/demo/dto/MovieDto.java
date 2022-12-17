package com.reactiveexample.demo.dto;

import java.util.List;

import com.reactiveexample.demo.model.Actor;
import com.reactiveexample.demo.model.MovieDetail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

 } 