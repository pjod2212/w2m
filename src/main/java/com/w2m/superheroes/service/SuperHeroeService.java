package com.w2m.superheroes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.w2m.superheroes.model.Heroe;


@Service
public interface SuperHeroeService {
	
	List<Heroe> findByFiltro(String filtro);
	
	Heroe modificarHeroe(Heroe heroe);
	
	Optional<Heroe> findById(Long id);
	
	void deleteById(Long id);
	
	List<Heroe> findAll();
	
	
	
	
}
