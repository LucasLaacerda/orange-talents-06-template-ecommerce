package br.com.zupacademy.lucaslacerda.mercadolivre.produto.opiniao;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;
import br.com.zupacademy.lucaslacerda.mercadolivre.validacao.VerificaIdValid;

public class OpiniaoForm {
	
	@NotBlank
	private String titulo;
	
	@NotBlank @Length(max=500)
	private String descricao;
	
	@NotNull
	@Min(value = 1) @Max(value = 5)
	private Integer nota; 
	
	@NotNull
	@VerificaIdValid(message = "Produto inexistente",entidade=Produto.class,campoId = "id", permiteNull = "false")
	private Long produto;

	public OpiniaoForm() {
		
	}

	public OpiniaoForm(@NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
			@NotNull @Min(1) @Max(5) Integer nota, @NotNull Long produto) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
		this.produto = produto;
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

	public Long getProduto() {
		return produto;
	}

	public Opiniao toModel(EntityManager manager, Usuario usuarioLogado) {
		return new Opiniao(titulo, descricao, nota, 
						manager.find(Produto.class, produto), usuarioLogado);
	}
	
	

	
	
}
