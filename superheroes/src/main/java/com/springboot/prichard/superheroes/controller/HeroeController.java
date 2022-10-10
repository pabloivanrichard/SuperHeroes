package com.springboot.prichard.superheroes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.prichard.superheroes.Mapper.HeroeRequestMapper;
import com.springboot.prichard.superheroes.Mapper.HeroeResposeMapper;
import com.springboot.prichard.superheroes.dto.HeroeRequest;
import com.springboot.prichard.superheroes.dto.HeroeResponse;
import com.springboot.prichard.superheroes.entity.Heroe;
import com.springboot.prichard.superheroes.exception.BusinessRuleException;
import com.springboot.prichard.superheroes.service.IHeroeService;

import ch.qos.logback.core.status.Status;

/**
 *
 * @author prichard
 */

@RestController
@RequestMapping("/api")
public class HeroeController {

	@Autowired
	IHeroeService heroeService;

	@Autowired
	HeroeRequestMapper requestHeroe;

	@Autowired
	HeroeResposeMapper responsetHeroe;

	public HeroeController(IHeroeService heroe) {
		this.heroeService = heroe;
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Bienvenidos a Api";
	}

	@Cacheable("tasks")
	@GetMapping("/Listar")
	public ResponseEntity<List<HeroeResponse>> getALLHeroes() {
		System.out.println("entro primera vez");
		List<Heroe> listadoHeroe = heroeService.findAll();
		List<HeroeResponse> listaHeroeReponse = responsetHeroe.HeroeListToHeroeResposeList(listadoHeroe);
		return ResponseEntity.ok().body(listaHeroeReponse);

	}

	@Cacheable("tasks")
	@RequestMapping(path = "/buscarByid/{idHeroe}", method = RequestMethod.GET)
	public ResponseEntity<HeroeResponse> buscarByid(@PathVariable long idHeroe) throws BusinessRuleException {
		Optional<Heroe> heroes = heroeService.findById(idHeroe);
		HeroeResponse HeroeResponse = responsetHeroe.HeroeToHeroeRespose(heroes.get());
		return ResponseEntity.ok().body(HeroeResponse);
	}

	@Cacheable("tasks")
	@RequestMapping(path = "/buscarByContainsNombre/{nombre}", method = RequestMethod.GET)
	public ResponseEntity<List<HeroeResponse>> buscarByContainsNombre(@PathVariable String nombre)
			throws BusinessRuleException {
		List<Heroe> listadoHeroe = heroeService.buscarByContainsNombre(nombre.toUpperCase());
		List<HeroeResponse> listaHeroeReponse = responsetHeroe.HeroeListToHeroeResposeList(listadoHeroe);
		return ResponseEntity.ok().body(listaHeroeReponse);
	}

	// @CacheEvict(value = "tasks")
	@CacheEvict(cacheNames = "tasks", allEntries = true)
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@RequestBody HeroeRequest input) {
		Heroe HeroeRequestToHeroe = requestHeroe.HeroeRequestToHeroe(input);
		Heroe save = heroeService.guardarHeroe(HeroeRequestToHeroe);
		HeroeResponse HeroeToHeroeRespose = responsetHeroe.HeroeToHeroeRespose(save);
		return ResponseEntity.ok(HeroeToHeroeRespose);
	}

	@CacheEvict(cacheNames = "tasks", allEntries = true)
	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable String id, @RequestBody HeroeRequest input)
			throws NumberFormatException, BusinessRuleException {
		Heroe save = null;
		Optional<Heroe> findById = heroeService.findById(Long.parseLong(id));
		Heroe get = findById.get();
		if (get != null) {
			save = requestHeroe.HeroeRequestToHeroe(input);
			save = heroeService.guardarHeroe(save);
		}
		return ResponseEntity.ok(save);
	}

	@CacheEvict(cacheNames = "tasks", allEntries = true)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) throws NumberFormatException, BusinessRuleException {
		Optional<Heroe> findById = heroeService.findById(Long.parseLong(id));
		Heroe get = findById.get();
		if (get != null) {
			heroeService.eliminarHeroe(get);
		}
		return ResponseEntity.ok().build();
	}

}
