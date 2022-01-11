package br.com.petbytes.adotantes.integration.ports.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.petbytes.adotantes.databuilders.AdotanteDataBuilder;
import br.com.petbytes.adotantes.domain.entities.Adotante;
import br.com.petbytes.adotantes.domain.valueobjects.identificacao.Identificacao;
import br.com.petbytes.adotantes.domain.valueobjects.identificacao.TipoIdentificacao;

@SpringBootTest
@AutoConfigureMockMvc
public class AdotanteControllerTests {
	
	@Autowired
    private MockMvc mvc;
	
	private AdotanteDataBuilder dataBuilder = new AdotanteDataBuilder();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	@Order(1)
	@DisplayName("Given a new adotante, when create is called, then adotante is created.")
	public void createAdotante() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));
		
		Adotante adotante = dataBuilder
			.withNome("Carlos Andrade")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		String content = mapper.writeValueAsString(adotante);
		
		// WHEN
		mvc.perform(post("/adotantes")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
			.accept(MediaType.APPLICATION_JSON))
		
		// THEN
			.andExpect(status().isCreated());
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Given a adotante, when findById is called, then return adotante.")
	public void findAdotanteById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));
		
		Adotante adotante = dataBuilder
			.withNome("Carlos Andrade")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		String content = mapper.writeValueAsString(adotante);
		
		String locationHeader = mvc.perform(post("/adotantes")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location");
		
		// WHEN
		mvc.perform(get(locationHeader)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
		
		// THEN
			.andExpect(status().isOk());
		
	}
	
	@Test
	@Order(3)
	@DisplayName("Given a adotante, when updateAdotanteById is called, then return adotante with updated values.")
	public void updateAdotanteById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));
		
		Adotante adotante = dataBuilder
			.withNome("Carlos Andrade")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		String content = mapper.writeValueAsString(adotante);
		
		String locationHeader = mvc.perform(post("/adotantes")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location");
		
		String newName = "Carlos Morais";
		
		adotante.setNome(newName);
		
		String newContent = mapper.writeValueAsString(adotante);
		
		// WHEN
		String responseBody = mvc.perform(put(locationHeader)
			.contentType(MediaType.APPLICATION_JSON)
			.content(newContent)
			.accept(MediaType.APPLICATION_JSON))
		
		// THEN
			.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		
		assertThat(responseBody).contains(newName);
		
	}
	
	@Test
	@Order(4)
	@DisplayName("Given a adotante, when deleteAdotanteById is called, then delete adotante.")
	public void deleteAdotanteById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));
		
		Adotante adotante = dataBuilder
			.withNome("Carlos Silva")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		String content = mapper.writeValueAsString(adotante);
		
		String locationHeader = mvc.perform(post("/adotantes")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location");
		
		// WHEN
		mvc.perform(delete(locationHeader)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
		
		// THEN
			.andExpect(status().isOk());
		
	}

}
