package br.com.zupacademy.lucaslacerda.mercadolivre.produto.detalhe;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.imagem.ImagemProduto;

public class ImagensDto {

	private String link;

	public ImagensDto(ImagemProduto imagem) {
		super();
		this.link = imagem.getLink();
	}


	public String getLink() {
		return link;
	}
		
}
