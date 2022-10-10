package com.springboot.prichard.superheroes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.prichard.superheroes.entity.Heroe;
import com.springboot.prichard.superheroes.exception.BusinessRuleException;

@ExtendWith(MockitoExtension.class)
public class HeroeServiceTest {

	  @Mock private HeroeService heroeService;
	  
	  
	    @BeforeEach void setUp()
	    {	    
	        
	    }
	    
	 
	    @Test void findAllHeroe()
	    {
	    	List<Heroe> list = new ArrayList<Heroe>();
	    	Heroe heroe1 = new Heroe();
	    	heroe1.setDescripcion("SUPERMAN");
	    	Heroe heroe2 = new Heroe();
	    	heroe2.setDescripcion("HIJITUS");
	    	Heroe heroe3 = new Heroe();
	    	heroe3.setDescripcion("VEGETTA");

			list.add(heroe1);
			list.add(heroe2);
			list.add(heroe3);

			when(heroeService.findAll()).thenReturn(list);
			
			//test
			List<Heroe> listHeroe = heroeService.findAll();

			assertEquals(3, listHeroe.size());
			verify(heroeService, times(1)).findAll();
	    }
	    
		@Test
		public void getByIdTest() throws BusinessRuleException
		{
		    Optional<Heroe>  heroeOptional;
			Heroe heroe=new Heroe();
			heroe.setDescripcion("SUPERMAN");
			heroe.setId(1L);
			heroeOptional=Optional.of(heroe);
			System.out.print(heroeOptional);
			when(heroeService.findById(1L)).thenReturn(heroeOptional);

			Heroe h = heroeService.findById(1L).get();

			assertEquals("SUPERMAN", h.getDescripcion());
			
		}
		

	  
}
