package com.reactiveexample.demo.model;

import java.util.Date;
import org.springframework.data.annotation.Id;

import lombok.Data;

 @Data
public class BaseDocument{
	 
	 @Id
	 private String id;
	 private  Date created_at;
	 private  Date updated_at;

}