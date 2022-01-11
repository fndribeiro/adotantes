package br.com.petbytes.adotantes.ports.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.petbytes.adotantes.application.datamappers.AdotanteMongoDataMapper;
import br.com.petbytes.adotantes.domain.valueobjects.identificacao.Identificacao;

@Repository
public interface AdotanteMongoRepository extends MongoRepository<AdotanteMongoDataMapper, String> {
	
	AdotanteMongoDataMapper findByIdentificacao(Identificacao identificacao);

}
