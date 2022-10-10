package com.springboot.prichard.superheroes.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.springboot.prichard.superheroes.entity.Heroe;


@DataJpaTest
public class HeroeIntegrationTest {
	
	  @Autowired
	  private TestEntityManager entityManager;
	  @Autowired
	  private IHeroeRepository heroeRepository;
	  
	  @Test
	  public void whenFindByName_hEROE() {
	    // given
	    Heroe heroe = createHeroe();
	    entityManager.persist(heroe);
	    entityManager.flush();
	    // when
	    Heroe b = heroeRepository.findByNameHeroe("SUPERPABLO");
	    // then
	    assertThat(b.getDescripcion())
	        .isEqualTo(heroe.getDescripcion());
	  }
	  private Heroe createHeroe() {
	    Heroe heroe = new Heroe();
	    heroe.setDescripcion("SUPERPABLO");
	    return heroe;
	  }
	  
  
	  @Test
	    void testFindById() {
	        java.util.Optional<Heroe> heroe = heroeRepository.findById(1L);
	        assertTrue(heroe.isPresent());
	        assertEquals("SUPERMAN", heroe.get().getDescripcion());
	    }


	    @Test
	    void testFindAll() {
	        List<Heroe> heroes = heroeRepository.findAll();
	        assertFalse(heroes.isEmpty());
	        assertEquals(6, heroes.size());
	    }

	    @Test
	    void testSave() {
	        // Given
	        Heroe heroe = new Heroe();
	        heroe.setDescripcion("SUPERMAN");

	        // When
	        Heroe heroeres = heroeRepository.save(heroe);


	        // Then
	        assertEquals("SUPERMAN", heroeres.getDescripcion());

	    }

	    

	    @Test
	    void testUpdate() {
	        // Given
	    	   Heroe heroe = new Heroe();
		        heroe.setDescripcion("SUPERMAN");


	        // When
	        Heroe heroeres = heroeRepository.save(heroe);

	        // Then
	        assertEquals("SUPERMAN", heroe.getDescripcion());

	        // When
	        heroe.setDescripcion("SUPERMAN1");
	        Heroe heroeActualizado = heroeRepository.save(heroe);

	        // Then
	        assertEquals("SUPERMAN1", heroeActualizado.getDescripcion());

	    }

	    @Test
	    void testDelete() {
	        Heroe heroe = heroeRepository.findById(1L).orElseThrow(null);
	        assertEquals("SUPERMAN", heroe.getDescripcion());
	        System.out.println("tamanio"+heroeRepository.findAll().size());
	        heroeRepository.delete(heroe);
	        System.out.println("tamanio1"+heroeRepository.findAll().size());
	        assertEquals(5, heroeRepository.findAll().size());
	    }

}
