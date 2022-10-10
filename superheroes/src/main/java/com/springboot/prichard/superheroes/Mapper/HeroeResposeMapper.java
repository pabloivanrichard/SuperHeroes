/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.prichard.superheroes.Mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.springboot.prichard.superheroes.dto.HeroeResponse;
import com.springboot.prichard.superheroes.entity.Heroe;

/**
 *
 * @author prichard
 */
@Mapper(componentModel = "spring")
public interface HeroeResposeMapper {

	@Mappings({})
	HeroeResponse HeroeToHeroeRespose(Heroe source);

	List<HeroeResponse> HeroeListToHeroeResposeList(List<Heroe> source);

}
