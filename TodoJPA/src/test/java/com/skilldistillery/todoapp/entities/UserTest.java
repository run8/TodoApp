package com.skilldistillery.todoapp.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user; 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	    emf = Persistence.createEntityManagerFactory("TodoJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close(); 
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager(); 
		user = em.find(User.class, 1); 
 	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
		user = null; 
	}

	@Test
	@DisplayName("testing User mappings")
	void test1() {
//		select * from user where id =1;
//		+----+------------------------+----------+--------------------------------------------------------------+---------+------+
//		| id | email                  | username | password                                                     | enabled | role |
//		+----+------------------------+----------+--------------------------------------------------------------+---------+------+
//		|  1 | shaun@winchester.co.uk | shaun    | $2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm |       1 | NULL |
//		+----+------------------------+----------+--------------------------------------------------------------+---------+------+
		assertNotNull(user); 
		assertEquals("shaun@winchester.co.uk", user.getEmail()); 
		assertEquals("$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm", user.getPassword()); 

	}

}
