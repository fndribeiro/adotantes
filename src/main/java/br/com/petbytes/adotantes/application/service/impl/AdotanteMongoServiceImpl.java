package br.com.petbytes.adotantes.application.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.petbytes.adotantes.application.datamappers.AdotanteMongoDataMapper;
import br.com.petbytes.adotantes.application.dtos.AdotanteDTO;
import br.com.petbytes.adotantes.application.service.AdotanteService;
import br.com.petbytes.adotantes.domain.entities.Adotante;
import br.com.petbytes.adotantes.domain.valueobjects.identificacao.Identificacao;
import br.com.petbytes.adotantes.ports.repositories.AdotanteMongoRepository;
import io.micrometer.core.instrument.util.StringUtils;

@Service
public class AdotanteMongoServiceImpl implements AdotanteService {
	
	private AdotanteMongoRepository repository;
	
	public AdotanteMongoServiceImpl(AdotanteMongoRepository repository) {
		this.repository = repository;
	}

	@Override
	public AdotanteDTO createAdotante(Adotante adotanteRequest) {
		
		var dataMapper = new AdotanteMongoDataMapper(adotanteRequest);
		
		AdotanteMongoDataMapper adotante = repository.save(dataMapper);
		
		return new AdotanteDTO(
				adotante.getId(), 
				adotante.getNome(), 
				adotante.getEndereco());
	}

	@Override
	public AdotanteDTO findAdotanteById(String adotanteId) {
		
		Optional<AdotanteMongoDataMapper> adotante = repository.findById(adotanteId);
		
		if (adotante.isEmpty()) {
			return null;
		}
		
		return new AdotanteDTO(
				adotante.get().getId(), 
				adotante.get().getNome(), 
				adotante.get().getEndereco());
	}
	
	@Override
	public AdotanteDTO findAdotanteByIdentificacao(Identificacao identificacao) {
		
		AdotanteMongoDataMapper adotante = repository.findByIdentificacao(identificacao);
		
		if (adotante == null) {
			return null;
		}
		
		return new AdotanteDTO(
				adotante.getId(), 
				adotante.getNome(), 
				adotante.getEndereco());
	}

	@Override
	public AdotanteDTO updateAdotante(String adotanteId, Adotante adotanteRequest) {
		
		Optional<AdotanteMongoDataMapper> adotante = repository.findById(adotanteId);
		
		if (adotante.isEmpty()) {
			return null;
		}
		
		adotante.map(a -> {
			
			if (StringUtils.isNotBlank(adotanteRequest.getNome())) {
				a.setNome(adotanteRequest.getNome());
			}
			
			if (adotanteRequest.getEndereco() != null) {
				a.setEndereco(adotanteRequest.getEndereco());
			}
			
			if (adotanteRequest.getIdentificacao() != null) {
				a.setIdentificacao(adotanteRequest.getIdentificacao());
			}
			
			return repository.save(a);
			
		});
		
		return new AdotanteDTO(
				adotante.get().getId(), 
				adotante.get().getNome(), 
				adotante.get().getEndereco());
	}

	@Override
	public void deleteAdotante(String adotanteId) {
		repository.deleteById(adotanteId);
	}

}
