package br.com.petbytes.adotantes.application.service;

import br.com.petbytes.adotantes.application.dtos.AdotanteDTO;
import br.com.petbytes.adotantes.domain.entities.Adotante;
import br.com.petbytes.adotantes.domain.valueobjects.endereco.Endereco;
import br.com.petbytes.adotantes.domain.valueobjects.identificacao.Identificacao;
import io.micrometer.core.instrument.util.StringUtils;

public interface AdotanteService {

	public AdotanteDTO createAdotante(Adotante adotanteRequest);
	
	public AdotanteDTO findAdotanteById(String adotanteId);
	
	public AdotanteDTO findAdotanteByIdentificacao(Identificacao identificacao);
	
	public AdotanteDTO updateAdotante(String adotanteId, Adotante adotanteRequest);
	
	public void deleteAdotante(String adotanteId);
	
	default boolean isInvalidEndereco(Endereco endereco) {
		return endereco == null
				|| endereco.getLogradouro() == null
				|| StringUtils.isBlank(endereco.getNome())
				|| StringUtils.isBlank(endereco.getNumero())
				|| StringUtils.isBlank(endereco.getCep())
				|| endereco.getCidade() == null
				|| endereco.getTipoResidencia() == null;
	}
	
}
