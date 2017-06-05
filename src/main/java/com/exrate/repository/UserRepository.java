package com.exrate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import com.exrate.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public User save(User user);
	public User findUserByEmail(String email);

}
