package com.w2m.superheroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.w2m.superheroes.model.Heroe;

@Repository
public interface SuperHeroesRepository extends CrudRepository<Heroe,Long> {

	List<Heroe> findByNombre(String filtro);	
    
    @Query(value = "select * from heroe where lower(nombre) like %:filtroParam%", 
    		  nativeQuery = true)
    List<Heroe> filtro(@Param("filtroParam") String filtro);
}