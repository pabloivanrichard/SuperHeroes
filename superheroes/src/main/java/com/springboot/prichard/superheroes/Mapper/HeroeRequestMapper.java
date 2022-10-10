/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.prichard.superheroes.Mapper;

import com.springboot.prichard.superheroes.dto.*;
import com.springboot.prichard.superheroes.entity.Heroe;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author prichard
 */
@Mapper(componentModel = "spring")
public interface HeroeRequestMapper {

	@Mappings({})
	Heroe HeroeRequestToHeroe(HeroeRequest source);

	List<Heroe> HeroeRequestListToHeroeList(List<HeroeRequest> source);

}
