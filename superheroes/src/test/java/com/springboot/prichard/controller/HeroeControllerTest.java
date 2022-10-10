package com.springboot.prichard.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.prichard.superheroes.Mapper.HeroeRequestMapper;
import com.springboot.prichard.superheroes.Mapper.HeroeResposeMapper;
import com.springboot.prichard.superheroes.controller.HeroeController;
import com.springboot.prichard.superheroes.entity.Heroe;
import com.springboot.prichard.superheroes.service.HeroeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(HeroeController.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = HeroeController.class)

//@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(HeroeController.class)
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeroeController.class)
@RunWith(SpringJUnit4ClassRunner.class)
 class HeroeControllerTest {
	

	@Autowired
	private MockMvc mvc;

	@MockBean
	private HeroeService heroeService;

	ObjectMapper objectMapper;

	
	@Autowired
	HeroeRequestMapper requestHeroe;

	@Autowired
	HeroeResposeMapper responsetHeroe;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		
	}


	@Test
	void findById() throws Exception {

		Heroe heroe = new Heroe();
		heroe.setId(1L);
		heroe.setDescripcion("SPIDERMAN");
		Optional<Heroe> heroeResponse = null;
		Optional.of(heroe);

		// Given
		when(heroeService.findById(1L)).thenReturn(heroeResponse);

		// When
		mvc.perform(get("/api/buscarByid/1").contentType(MediaType.APPLICATION_JSON))
				// Then
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.descripcion").value("SPIDERMAN"));

		verify(heroeService).findById(1L);
		
	}
	
	
    @Test
    void testListar() throws Exception {
    	
        // Given
    	List<Heroe> listaheroes = new ArrayList<Heroe>();
    	Heroe heroe1 = new Heroe();
    	heroe1.setId(1L);
    	heroe1.setDescripcion("SUPERMAN");
    	heroe1.setId(2L);
    	Heroe heroe2 = new Heroe();
    	heroe2.setDescripcion("HIJITUS");
    	heroe1.setId(3L);
    	Heroe heroe3 = new Heroe();
    	heroe3.setDescripcion("VEGETTA");

    	listaheroes.add(heroe1);
    	listaheroes.add(heroe2);
    	listaheroes.add(heroe3);

		when(heroeService.findAll()).thenReturn(listaheroes);

        // When
        mvc.perform(get("/api/Listar").contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].descripcion").value("SUPERMAN"))
                .andExpect(jsonPath("$[1].descripcion").value("HIJITUS"))
                .andExpect(jsonPath("$[2].descripcion").value("VEGETTA"))
                .andExpect(content().json(objectMapper.writeValueAsString(listaheroes)));

        verify(heroeService).findAll();
        
    }

    @Test
    void testGuardar() throws Exception {
    	
        // Given
        Heroe heroe = new Heroe();
        heroe.setDescripcion("HULK");
        when(heroeService.guardarHeroe(any())).then(invocation ->{
            Heroe c = invocation.getArgument(0);
            c.setId(4L);
            return c;
        });

        // when
        mvc.perform(post("/api/guardar").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroe)))
        // Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.descripcion", is("HULK")));
        verify(heroeService).guardarHeroe(any());

    }
	

}

