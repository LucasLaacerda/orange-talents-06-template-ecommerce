package br.com.zupacademy.lucaslacerda.mercadolivre.produto.detalhe;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.opiniao.Opiniao;

public class OpiniaoDto {

	private String titulo;
	private String descricao;
	private Integer nota;
	
	public OpiniaoDto() {
		
	} 
	
	public OpiniaoDto(Opiniao opiniao) {
		super();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.nota = opiniao.getNota();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	} 
	
	
}
