package com.reactiveexample.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class Role implements GrantedAuthority {
 
  @Id
  private String id;
  
  private String role;
  
  public String getAuthority() {
    return this.role;
  }
}
