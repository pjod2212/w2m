package com.w2m.superheroes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2m.superheroes.model.Heroe;
import com.w2m.superheroes.repository.SuperHeroesRepository;

@Service
public class SuperHeroeServiceImpl implements SuperHeroeService {

	@Autowired
	SuperHeroesRepository repository;
	
	@Override
	public List<Heroe> findByFiltro(String filtro) {		
		return repository.filtro(filtro);
	}

	@Override
	public List<Heroe> findAll() {
		
		return (List<Heroe>) repository.findAll();
	}

	@Override
	public Optional<Heroe> findById(Long id) {		
		return repository.findById(id);
	}

	@Override
	public Heroe modificarHeroe(Heroe heroe) {
		return repository.save(heroe);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}


}
