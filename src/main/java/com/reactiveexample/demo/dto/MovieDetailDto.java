package com.reactiveexample.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class MovieDetailDto {
	
	    private String id;
		 
	    @NotBlank
	    @Size(max = 140)
	    private String details;
}

