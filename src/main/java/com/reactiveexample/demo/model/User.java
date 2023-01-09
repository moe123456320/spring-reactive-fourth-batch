package com.reactiveexample.demo.model;

import java.util.List;

/*import jakarta.validation.constraints.Size;*/

import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {

  @Id
  private String id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  private String username;
 
  private String email;

  @Size(min = 4, message = "Minimum password length: 8 characters")
  private String password;

  private List<Role> roles;
}
