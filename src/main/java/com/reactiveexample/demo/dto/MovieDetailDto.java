package com.reactiveexample.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieDetailDto {
	
	    private String id;
		 
	    @NotBlank
	    @Size(max = 140)
	    private String details;
}

