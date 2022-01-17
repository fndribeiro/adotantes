package br.com.petbytes.adotantes.ports.controllers;

import java.net.URI;
import java.text.MessageFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petbytes.adotantes.application.dtos.AdotanteDTO;
import br.com.petbytes.adotantes.application.service.AdotanteService;
import br.com.petbytes.adotantes.domain.entities.Adotante;
import br.com.petbytes.adotantes.ports.controllers.exceptions.NotFoundException;
import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping("/adotantes")
public class AdotanteController {
	
	private AdotanteService service;
	
	private final String notFoundMessage = "Adotante not for found for the given id.";

	public AdotanteController(AdotanteService service) {
		this.service = service;
	}
	
	@PostMapping
	ResponseEntity<String> createAdotante(@RequestBody Adotante adotanteRequest) {
		
		if (StringUtils.isBlank(adotanteRequest.getNome())) {
			return ResponseEntity.badRequest().body("Name must not be null or empty.");
		}
		
		if (service.isInvalidEndereco(adotanteRequest.getEndereco())) {
			return ResponseEntity.badRequest().body("Invalid adress. Please check if adress has logradouro, nome, numero, cep and cidade.");
		}
		
		if (adotanteRequest.getIdentificacao() == null) {
			return ResponseEntity.badRequest().body("Identificacao must not be null.");
		}
		
		AdotanteDTO adotanteFoundByIdentificacao = service.findAdotanteByIdentificacao(adotanteRequest.getIdentificacao());
		
		if (adotanteFoundByIdentificacao != null) {
			return ResponseEntity.badRequest().body("The given Identificacao is already registered to " + adotanteFoundByIdentificacao.getNome() + ".");
		};
		
		AdotanteDTO adotante = service.createAdotante(adotanteRequest);
		
		URI location = URI.create(MessageFormat.format("/adotantes/{0}", adotante.getId()));
			
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/{id}")
	ResponseEntity<AdotanteDTO> findAdotanteById(@PathVariable(value = "id") String id) {
		
		AdotanteDTO adotante = service.findAdotanteById(id);
		
		if (adotante == null) {
			throw new NotFoundException(notFoundMessage);
		}
		
		return ResponseEntity.ok(adotante);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<AdotanteDTO> updateAdotanteById(@PathVariable(value = "id") String id, @RequestBody Adotante adotanteRequest) {
		
		AdotanteDTO adotante = service.updateAdotante(id, adotanteRequest);
		
		if (adotante == null) {
			throw new NotFoundException(notFoundMessage);
		}
		
		return ResponseEntity.ok(adotante);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteAdotanteById(@PathVariable(value = "id") String id) {
		
		service.deleteAdotante(id);
			
		return ResponseEntity.ok("Entity deleted successfully.");
			
	}

}
