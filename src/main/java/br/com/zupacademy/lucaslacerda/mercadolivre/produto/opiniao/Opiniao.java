package br.com.zupacademy.lucaslacerda.mercadolivre.produto.opiniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private String titulo;
	
	@NotNull
	@Column(nullable=false)
	private String descricao;
	
	@NotNull
	@Column(nullable=false)
	private Integer nota; 
	
	@ManyToOne
	@NotNull @Valid
	private Produto produto;

	@ManyToOne
	@NotNull @Valid
	private Usuario remetente;

	@Deprecated
	public Opiniao() {

	}
	
	public Opiniao(@NotNull String titulo, @NotNull String descricao, @NotNull Integer nota,
			@NotNull @Valid Produto produto, @NotNull @Valid Usuario remetente) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
		this.produto = produto;
		this.remetente = remetente;
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

	public Produto getProduto() {
		return produto;
	}

	public Usuario getRemetente() {
		return remetente;
	}
	
	
}
