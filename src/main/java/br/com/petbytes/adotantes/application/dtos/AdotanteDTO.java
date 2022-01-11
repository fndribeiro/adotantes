package br.com.petbytes.adotantes.application.dtos;

import br.com.petbytes.adotantes.domain.valueobjects.endereco.Endereco;

public class AdotanteDTO {
	
	private String id;
	private String nome;
	private Endereco endereco;
	
	public AdotanteDTO(String id, String nome, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
}
