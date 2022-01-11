package br.com.petbytes.adotantes.domain.valueobjects.endereco;

public class Endereco {
	
	private Logradouro logradouro;
	private String nome;
	private String numero;
	private String complemento;
	private String cep;
	private Cidade cidade;
	private TipoResidencia tipoResidencia;
	
	public Endereco() {}

	public Endereco(Logradouro logradouro, String nome, String numero, String complemento, String cep, Cidade cidade, TipoResidencia tipoResidencia) {
		this.logradouro = logradouro;
		this.nome = nome;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.cidade = cidade;
		this.tipoResidencia = tipoResidencia;
	}

	public Endereco(Logradouro logradouro, String nome, String numero, String cep, Cidade cidade) {
		this.logradouro = logradouro;
		this.nome = nome;
		this.numero = numero;
		this.cep = cep;
		this.cidade = cidade;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCep() {
		return cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public TipoResidencia getTipoResidencia() {
		return tipoResidencia;
	}
	
}
