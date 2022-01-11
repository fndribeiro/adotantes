package br.com.petbytes.adotantes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.petbytes.adotantes.ports.controllers.AdotanteController;

@SpringBootTest
class PetbytesAdotantesApplicationTests {
	
	@Autowired
	private AdotanteController petController;

	@Test
	void contextLoads() {
		
		assertThat(petController).isNotNull();
		
	}

}
