package br.com.petbytes.adotantes.integration.ports.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.petbytes.adotantes.application.datamappers.AdotanteMongoDataMapper;
import br.com.petbytes.adotantes.databuilders.AdotanteDataBuilder;
import br.com.petbytes.adotantes.domain.entities.Adotante;
import br.com.petbytes.adotantes.domain.valueobjects.identificacao.Identificacao;
import br.com.petbytes.adotantes.domain.valueobjects.identificacao.TipoIdentificacao;
import br.com.petbytes.adotantes.ports.repositories.AdotanteMongoRepository;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class AdotanteMongoRepositoryTests {

	@Autowired 
	private AdotanteMongoRepository repository;
	
	private AdotanteDataBuilder dataBuilder = new AdotanteDataBuilder();
	
	@Test
	@Order(1)
	@DisplayName("Given a Identificacao, when that Identificacao exists, return Adotante.")
	public void findAdotanteByIdentificacao() {
		
		// GIVEN
		String numeroIdentificacao = "40062067044";
		
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, numeroIdentificacao);
		
		Adotante adotante = dataBuilder
			.withNome("Jailson Jorge Silva")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		AdotanteMongoDataMapper adotanteDataMapper = new AdotanteMongoDataMapper(adotante);
		
		repository.save(adotanteDataMapper);
		
		// WHEN
		AdotanteMongoDataMapper adotanteFound = repository.findByIdentificacao(identificacao);

		// THEN
		assertThat(adotanteDataMapper.getIdentificacao()).isEqualTo(adotanteFound.getIdentificacao());
		
		// FINALLY
		repository.delete(adotanteDataMapper);
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Given a Identificacao, when that Identificacao does not exist, return null.")
	public void findAdotanteByIdentificacaoAndReturnNullWhenAdotanteIsNotFound() {
		
		// GIVEN
		String numeroIdentificacao = "123456789";
		
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, numeroIdentificacao);
		
		// WHEN
		AdotanteMongoDataMapper adotanteFound = repository.findByIdentificacao(identificacao);

		// THEN
		assertThat(adotanteFound).isNull();
		
	}
	
}
