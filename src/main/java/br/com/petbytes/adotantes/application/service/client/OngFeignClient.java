package br.com.petbytes.adotantes.application.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.petbytes.adotantes.domain.entities.Pet;

@FeignClient("ongs")
public interface OngFeignClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/pets/byong/{id}", consumes = "application/json")
	List<Pet> findAllPetsByOngId(@PathVariable(value = "id") String id);

}
