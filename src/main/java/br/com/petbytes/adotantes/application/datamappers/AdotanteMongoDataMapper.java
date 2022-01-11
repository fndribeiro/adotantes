package br.com.petbytes.adotantes.application.datamappers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.petbytes.adotantes.domain.entities.Adotante;

@Document("adotantes")
public class AdotanteMongoDataMapper extends Adotante {

	@Id
	private String id;

	public AdotanteMongoDataMapper() {}

	public AdotanteMongoDataMapper(Adotante adotante) {
		setNome(adotante.getNome());
		setEndereco(adotante.getEndereco());
		setIdentificacao(adotante.getIdentificacao());
	}
	
	public String getId() {
		return id;
	}
	
}
