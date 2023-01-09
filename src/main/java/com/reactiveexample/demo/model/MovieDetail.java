
package com.reactiveexample.demo.model;

/*import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
*/

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("movie_detail")
public class MovieDetail {
	
	@Id
    private String id;
	 
    private String details;
}
