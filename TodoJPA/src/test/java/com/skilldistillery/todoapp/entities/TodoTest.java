package com.skilldistillery.todoapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TodoTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Todo todo; 
	
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
		todo = em.find(Todo.class, 1); 
 	}

	@AfterEach
	void tearDown() throws Exception {
		em.close(); 
		todo = null; 
	}

	@Test
	@DisplayName("testing Todo mappings")
	void test1() {
//		select * from todo where id =1;
//		+----+----------------+-------------+-----------+---------+----------+---------------+---------------------+---------------------+
//		| id | task           | description | completed | user_id | due_date | complete_date | created_at          | updated_at          |
//		+----+----------------+-------------+-----------+---------+----------+---------------+---------------------+---------------------+
//		|  1 | Go round Mum's | NULL        |         1 |       1 | NULL     | NULL          | 2021-08-11 08:41:44 | 2021-08-11 08:41:44 |
//		+----+----------------+-------------+-----------+---------+----------+---------------+---------------------+---------------------+
		assertNotNull(todo); 
		assertEquals(1, todo.getUser().getId()); 
		assertEquals("Go round Mum's", todo.getTask()); 
		assertEquals("2021-08-11T08:41:44", todo.getCreatedAt().toString()); 
//		assertEquals("2021-08-11 08:41:44", todo.getUpdatedAt()); 
	}

//	@Test
//	@DisplayName("test Todo to User mapping")
//	void test3() {
//		assertNotNull(film); 
//		assertEquals("Japanese", film.getLanguage().getName()); 
//	}
}
