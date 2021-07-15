package br.com.zupacademy.lucaslacerda.mercadolivre.produto.detalhe;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.ProdutoCaracteristica;

public class CaracteristicaDto {

	private String nome;
	private String descricao;
	
	public CaracteristicaDto(ProdutoCaracteristica caracteristica) {
		super();
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getNome();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
