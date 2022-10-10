package com.springboot.prichard.superheroes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.prichard.superheroes.entity.Heroe;
import com.springboot.prichard.superheroes.exception.BusinessRuleException;
import com.springboot.prichard.superheroes.repository.IHeroeRepository;

@Service
public class HeroeService implements IHeroeService {

	@Autowired
	private IHeroeRepository heroeRepository;

	@Transactional
	public List<Heroe> findAll() {
		// TODO Auto-generated method stub
		return (List<Heroe>) heroeRepository.findAll();
	}

	public Heroe buscarByNombre(String nombre) {
		return heroeRepository.findByNameHeroe(nombre);
	}

	public List<Heroe> buscarByContainsNombre(String nombre) {
		return heroeRepository.findByNameContainsHeroe(nombre);
	}

	public Heroe guardarHeroe(Heroe heroe) {
		return heroeRepository.save(heroe);
	}

	public Heroe eliminarHeroe(Heroe heroe) {
		heroeRepository.delete(heroe);
		return null;
	}

	@Transactional
	@Override
	public Optional<Heroe> findById(Long id) throws BusinessRuleException {
		// TODO Auto-generated method stub
		Optional<Heroe> heroeResponse = heroeRepository.findById(id);
		if (heroeResponse.isPresent()) {
			return heroeResponse;
		} else {
			BusinessRuleException exception = new BusinessRuleException("1001", "Error de validacion, Heroe no existe",
					HttpStatus.PRECONDITION_FAILED);
			throw exception;

		}

	}

}
