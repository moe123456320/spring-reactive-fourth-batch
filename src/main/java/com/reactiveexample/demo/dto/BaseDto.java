package com.reactiveexample.demo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.reactiveexample.demo.model.Actor;
import com.reactiveexample.demo.model.MovieDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

 //@Data
// @Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BaseDto{		
	 
	//@Id
	 private String id;	
	 
	 
	 private  Date created_at;
	 
	 
	 private  Date updated_at;

	 
	 
	 

	public void setId(String id) {
		this.id = id;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}


	public String getId() {
		return id;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public Date getUpdated_at() {
		return updated_at;
	}
	 
	
	
	 
	 
	 
}