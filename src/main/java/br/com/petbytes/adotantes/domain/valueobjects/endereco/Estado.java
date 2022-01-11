package br.com.petbytes.adotantes.domain.valueobjects.endereco;

public class Estado {

	private String nome;
	private String sigla;
	private Pais pais;
	
	public Estado(String nome, String sigla, Pais pais) {
		this.nome = nome;
		this.sigla = sigla;
		this.pais = pais;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public Pais getPais() {
		return pais;
	}
	
}
