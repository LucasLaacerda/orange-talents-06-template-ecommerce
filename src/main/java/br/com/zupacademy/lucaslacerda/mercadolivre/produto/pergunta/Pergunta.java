package br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta;

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
public class Pergunta {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private String titulo;
	
	@ManyToOne
	@NotNull @Valid
	private Produto produto;

	@ManyToOne
	@NotNull @Valid
	private Usuario remetente;

	public Pergunta() {
		
	}	
	
	public Pergunta(@NotNull String titulo, @NotNull @Valid Produto produto, @NotNull @Valid Usuario remetente) {
		super();
		this.titulo = titulo;
		this.produto = produto;
		this.remetente = remetente;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getRemetente() {
		return remetente;
	}
	
	
	
	
}
