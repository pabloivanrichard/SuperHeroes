package com.springboot.prichard.superheroes.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.prichard.superheroes.entity.Heroe;

@SpringBootTest
public class HeroeRepositoryTest {
	
    @Autowired
    private IHeroeRepository heroeRepository;
    
    @Test
    void save() {
        Heroe heroe = new Heroe();
        heroe.setId(1);
        heroe.setDescripcion("SUPERMAN");
        heroeRepository.save(heroe);
        Optional<Heroe> actualResult = heroeRepository.findById(heroe.getId());
        System.out.println("Actual Result ="+ actualResult.get());
        System.out.println("heroe ="+ heroe.getId());
        assert heroe !=null;
    }
    
    @Test
    void findbyid() {
        Heroe heroe = new Heroe();
        heroe.setId(1);
        heroe.setDescripcion("SUPERMAN");
        heroeRepository.save(heroe);
        Optional<Heroe> actualResult = heroeRepository.findById(heroe.getId());
        assert actualResult.isPresent()==true;
    }

}
