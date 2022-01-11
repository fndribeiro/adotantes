package br.com.petbytes.adotantes.domain.valueobjects.endereco;

public class Cidade {

	private String nome;
	private Estado estado;
	
	public Cidade(String nome, Estado estado) {
		this.nome = nome;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public Estado getEstado() {
		return estado;
	}
	
}
