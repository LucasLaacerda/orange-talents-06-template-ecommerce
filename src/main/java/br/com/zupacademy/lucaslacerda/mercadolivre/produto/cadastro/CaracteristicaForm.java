package br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaForm {

		@NotBlank
		private String nome;
		@NotBlank
		private String descricao;

		public CaracteristicaForm(@NotBlank String nome,
				@NotBlank String descricao) {
			super();
			this.nome = nome;
			this.descricao = descricao;
		}

		public String getNome() {
			return nome;
		}

		public String getDescricao() {
			return descricao;
		}


		public ProdutoCaracteristica toModel(@NotNull @Valid Produto produto) {
			return new ProdutoCaracteristica(nome,descricao,produto);
		}



	
}
