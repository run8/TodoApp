package com.skilldistillery.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.skilldistillery.todoapp.entities.User;

@Service
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	
	User findByUsername(String username);

}
