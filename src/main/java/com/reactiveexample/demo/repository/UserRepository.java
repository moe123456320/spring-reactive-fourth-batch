package com.reactiveexample.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reactiveexample.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
}
