package com.w2m.superheroes.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.superheroes.model.Heroe;
import com.w2m.superheroes.repository.SuperHeroesRepository;
import com.w2m.superheroes.service.SuperHeroeService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SuperHeroesController {
	
	@Autowired
	SuperHeroeService service;
	
	@GetMapping("/heroes")
	public ResponseEntity<List<Heroe>> getAllHeroes(@RequestParam(required = false) String filtro ) {
		try {
			List<Heroe> heroes = new ArrayList<Heroe>();		
			
			if (filtro == null)
				service.findAll().forEach(heroes::add);
			else {
				service.findByFiltro(filtro).forEach(heroes::add);
			}
				
			if (heroes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(heroes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/heroe")
	public ResponseEntity<Optional<Heroe>> getHeroeById(@RequestParam(required = false) Long id ) {
		try {						
			Optional<Heroe> heroe = service.findById(id);
				
			if (heroe.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(heroe, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/heroe/{id}")
	public ResponseEntity<Heroe> modificarHeroe(@PathVariable Long id,@RequestBody Heroe heroe ) {
						
			 Optional<Heroe> heroeBd = service.findById(id);
			 
			 if(heroeBd.isPresent()){
				 Heroe _heroe = heroeBd.get();
				 _heroe.setNombre(heroe.getNombre());
							 
				return new ResponseEntity<>(service.modificarHeroe(_heroe), HttpStatus.OK);
			 }else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 }			
			
	}
	
	  @DeleteMapping("/heroe/{id}")
	  public ResponseEntity<HttpStatus> deleteHeroe(@PathVariable("id") Long id) {
	    try {
	    	service.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
