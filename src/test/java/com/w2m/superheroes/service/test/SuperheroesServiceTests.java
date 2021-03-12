package com.w2m.superheroes.service.test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.*;
import static org.assertj.core.api.Assertions.*;
import com.w2m.superheroes.service.SuperHeroeService;

@RunWith(SpringRunner.class)
@Sql("/db/data.sql")
@AutoConfigureTestDatabase
@SpringBootTest(properties = { "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" })
class SuperheroesServiceTests {
	
	@Autowired
	SuperHeroeService service;
	
	@Test
	void test() {			
		assertThat(service.findAll()).isNotEmpty();
	}

}
