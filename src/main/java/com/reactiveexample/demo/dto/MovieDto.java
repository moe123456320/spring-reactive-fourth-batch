package com.reactiveexample.demo.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.reactiveexample.demo.model.Actor;
import com.reactiveexample.demo.model.MovieDetail;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;


//import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode
public class MovieDto extends BaseDto {

	@NotBlank
    @Size(max = 140)
    private String name;
    
    @NotNull
    private Integer year;

    @NotNull
    private String director;
        
    private List<Actor> actors;

    MovieDetailDto details;
 } 