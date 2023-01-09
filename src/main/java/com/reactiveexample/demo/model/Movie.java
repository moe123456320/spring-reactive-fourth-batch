package com.reactiveexample.demo.model;

import java.util.Date;
import java.util.List;

/*import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
*/

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
//@EqualsAndHashCode
public class Movie extends BaseDocument {
	 	
	    private String name;	    	    
	    private Integer year;	    
	    private String director;	    	    
	    private List<Actor> actors;
		@DBRef
	    MovieDetail details;		
}
