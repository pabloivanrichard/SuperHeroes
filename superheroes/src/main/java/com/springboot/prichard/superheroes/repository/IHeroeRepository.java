package com.springboot.prichard.superheroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.prichard.superheroes.entity.Heroe;

public interface IHeroeRepository extends JpaRepository<Heroe, Long> {

	@Query(value = "SELECT * FROM heroe WHERE descripcion = ?1", nativeQuery = true)
	public Heroe findByNameHeroe(String nombre);

	@Query(value = "select * from heroe  where descripcion like %?1%", nativeQuery = true)
	public List<Heroe> findByNameContainsHeroe(String descripcion);

}
