package com.reactiveexample.demo.dto;

import java.util.Date;
import org.springframework.data.annotation.Id;
import lombok.Data;

 @Data
public class BaseDto{		
	 
	 private String id;	
	 private  Date created_at;	
	 private  Date updated_at;
}