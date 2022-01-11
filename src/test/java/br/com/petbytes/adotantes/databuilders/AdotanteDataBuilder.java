package br.com.petbytes.adotantes.databuilders;

import br.com.petbytes.adotantes.domain.entities.Adotante;
import br.com.petbytes.adotantes.domain.valueobjects.endereco.Cidade;
import br.com.petbytes.adotantes.domain.valueobjects.endereco.Endereco;
import br.com.petbytes.adotantes.domain.valueobjects.endereco.Estado;
import br.com.petbytes.adotantes.domain.valueobjects.endereco.Logradouro;
import br.com.petbytes.adotantes.domain.valueobjects.endereco.Pais;
import br.com.petbytes.adotantes.domain.valueobjects.endereco.TipoResidencia;
import br.com.petbytes.adotantes.domain.valueobjects.identificacao.Identificacao;

public class AdotanteDataBuilder {
	
	private Adotante adotante = new Adotante();
	
	public AdotanteDataBuilder withNome(String nome) {
		
		adotante.setNome(nome);
		
		return this;
	}
	
	public AdotanteDataBuilder withEndereco() {
		
		Pais pais = new Pais("Brasil");
		
		Estado estado = new Estado("Sao Paulo", "SP", pais);
		
		Cidade cidade = new Cidade("Sao Paulo", estado);
		
		String nomeRua = "Avenida Reboucas";
		String numero = "321";
		String complemento = "APTO 102030";
		String cep = "04555006";
		
		Endereco endereco = new Endereco(Logradouro.RUA, nomeRua, numero, complemento, cep, cidade, TipoResidencia.APARTAMENTO);
		
		adotante.setEndereco(endereco);
		
		return this;
	}
	
	public AdotanteDataBuilder withIdentificacao(Identificacao identificacao) {
		
		adotante.setIdentificacao(identificacao);
		
		return this;
	}
	
	public Adotante build() {
		return adotante;
	}

}
