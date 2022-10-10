package com.springboot.prichard.superheroes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.springboot.prichard.superheroes.entity.Heroe;
import com.springboot.prichard.superheroes.repository.IHeroeRepository;

@EnableCaching
@SpringBootApplication()
public class SuperheroesApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroesApplication.class);

	@Autowired
	IHeroeRepository heroeRepository;

	public static void main(String[] args) {
		LOGGER.info("inicio");
		SpringApplication.run(SuperheroesApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		Heroe heroe = new Heroe();
		heroe.setDescripcion("SUPERMAN");

		Heroe heroe1 = new Heroe();
		heroe1.setDescripcion("SPIDERMAN");

		Heroe heroe2 = new Heroe();
		heroe2.setDescripcion("HULK");

		Heroe heroe3 = new Heroe();
		heroe3.setDescripcion("CAPITAN AMERICA");

		Heroe heroe4 = new Heroe();
		heroe4.setDescripcion("THOR");

		Heroe heroe5 = new Heroe();
		heroe5.setDescripcion("GOKU");

		return () -> {
			heroeRepository.save(heroe);
			heroeRepository.save(heroe1);
			heroeRepository.save(heroe2);
			heroeRepository.save(heroe3);
			heroeRepository.save(heroe4);
			heroeRepository.save(heroe5);
		};

	}

}
