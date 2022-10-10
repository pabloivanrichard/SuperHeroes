package com.springboot.prichard.superheroes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.prichard.superheroes.entity.Heroe;
import com.springboot.prichard.superheroes.repository.IHeroeRepository;
import com.springboot.prichard.superheroes.service.HeroeService;

@SpringBootTest
class SuperheroesApplicationTests {
	

	@MockBean
	IHeroeRepository heroeRepository;

	@Autowired
	HeroeService service;

	@Test
	void contextLoads() {
		
	    Optional<Heroe>  heroeOptional;
			Heroe heroe=new Heroe();
			heroe.setDescripcion("SUPERMAN");
			heroe.setId(1L);
			heroeOptional=Optional.of(heroe);
		  System.out.println(heroeOptional.get().getDescripcion());
		    Optional<Heroe>  heroeOptional1;
					Heroe heroe1=new Heroe();
					heroe1.setDescripcion("SPIDERMAN");
					heroe1.setId(2L);
					heroeOptional1=Optional.of(heroe1);
				  System.out.println(heroeOptional1.get().getDescripcion());
		  
		  
		  		
		when(heroeRepository.findById(1L)).thenReturn(heroeOptional);
		when(heroeRepository.findById(2L)).thenReturn(heroeOptional1);
		
		String respuesta_1=heroeRepository.findById(1L).get().getDescripcion();
		String respuesta_2=heroeRepository.findById(2L).get().getDescripcion();
	
		assertEquals("SUPERMAN",respuesta_1);
		assertEquals("SPIDERMAN",respuesta_2);


	}

}
