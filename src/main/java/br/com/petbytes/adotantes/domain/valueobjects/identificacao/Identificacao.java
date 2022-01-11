package br.com.petbytes.adotantes.domain.valueobjects.identificacao;

import java.util.Objects;

public class Identificacao {
	
	private TipoIdentificacao tipoIdentificacao;
	private String numero;
	
	public Identificacao(TipoIdentificacao tipoIdentificacao, String numero) {
		this.tipoIdentificacao = tipoIdentificacao;
		this.numero = numero;
	}

	public TipoIdentificacao getTipoIdentificacao() {
		return tipoIdentificacao;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero, tipoIdentificacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identificacao other = (Identificacao) obj;
		return Objects.equals(numero, other.numero) && tipoIdentificacao == other.tipoIdentificacao;
	}

}
