package com.skilldistillery.todoapp.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.services.TodoService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class TodoController {

	@Autowired
	TodoService ts;

	//	private String username = "shaun";


//  GET todos
	@GetMapping("todos")
	public Set<Todo> index(
			HttpServletRequest req, 
			HttpServletResponse res,
			Principal principal) {
		return ts.index(principal.getName());
	}

//  GET todos/{tid}
	@GetMapping("todos/{tid}")
	public Todo show(
			HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int tid,
			Principal principal
			) {
		System.out.println("********* tid: " + tid);
		return ts.show(principal.getName(), tid);
	}

//  POST todos
	@PostMapping("todos")
	public Todo create(HttpServletRequest req, 
			HttpServletResponse res, 
			@RequestBody Todo todo,
			Principal principal) {
		try {
			todo = ts.create(principal.getName(), todo);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(todo.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			todo = null;		}
		return ts.create(principal.getName(), todo);
	}

//  PUT todos/{tid}
	@PutMapping("todos/{tid}")
	public Todo update(
			HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int tid, 
			@RequestBody Todo todo,
			Principal principal) {
		return ts.update(principal.getName(), tid, todo);
	}

//  DELETE todos/{tid}
	@DeleteMapping(path = "todos/{tid}")
	public void destroy(
			HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int tid,
			Principal principal) {
		ts.destroy(principal.getName(), tid);
	}

}
