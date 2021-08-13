package com.skilldistillery.todoapp.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.entities.User;
import com.skilldistillery.todoapp.repositories.TodoRepository;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	private TodoRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Set<Todo> index(String username) {
		Set<Todo> setTds = new HashSet<Todo>();
		setTds.addAll(repo.findAll());
		return setTds;
	
	}

	@Override
	public Todo show(String username, int tid) {
		Optional<Todo> todoOpt =repo.findById(tid);
		if (todoOpt.isPresent()) {
			return todoOpt.get();
		}
		return null;
	}

	@Override
	public Todo create(String username, Todo todo) {           // why username?
		System.out.println("**************** todo: " + todo);

//		User us1 = userRepo.findByUsername(username);
//		System.out.println("**************** after userRepo.findbyUsername" + us1);
//
//		if (todo.getUser() == null) {
//			todo.setUser(us1);
//		} else {
//			System.out.println("**************** in create() else");
//		}
		return repo.saveAndFlush(todo);
	}

	@Override
	public Todo update(String username, int tid, Todo todo) {
		Optional<Todo> td = repo.findById(tid);
		if (td.isPresent()) {
			return repo.saveAndFlush(todo);
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int tid) {
		repo.deleteById(tid);
		return !repo.findById(tid).isPresent();
//		Optional<Todo> td = repo.findById(tid);
//		if (td.isPresent()) {
//			 repo.delete(td.get());
//			 return true;
//		}
//		return false;
	}

}
