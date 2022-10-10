package com.springboot.prichard.superheroes.service;

import java.util.List;
import java.util.Optional;

import com.springboot.prichard.superheroes.entity.Heroe;
import com.springboot.prichard.superheroes.exception.BusinessRuleException;

public interface IHeroeService {

	public List<Heroe> findAll();
	public Heroe buscarByNombre(String nombre);
	public List<Heroe> buscarByContainsNombre(String nombre);
	public Heroe guardarHeroe(Heroe heroe);
	public Heroe eliminarHeroe(Heroe heroe);
	public Optional<Heroe> findById(Long id)throws BusinessRuleException;
}
