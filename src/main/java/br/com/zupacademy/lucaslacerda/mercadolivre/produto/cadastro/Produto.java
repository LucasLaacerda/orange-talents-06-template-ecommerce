package br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.lucaslacerda.mercadolivre.categoria.Categoria;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;

@Entity
public class Produto {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private String nome;
	
	@NotNull
	@Column(nullable=false)
	private BigDecimal valor;
	
	@NotNull
	@Column(nullable=false)
	private int quantidade;
	
	@OneToMany @Size(min=3)
	private Set<ProdutoCaracteristica> caracteristicas;
	
	@NotNull
	@Column(nullable=false)
	private String descricao;
	
	@ManyToOne
	private Categoria categoria;

	@ManyToOne
	private Usuario vendedor;
	
	private LocalDateTime instante = LocalDateTime.now();
	
	
	public Produto() {
		
	}
	

	public Produto(@NotNull String nome, @NotNull BigDecimal valor, @NotNull int quantidade,
			@Size(min = 3) Set<ProdutoCaracteristica> caracteristicas, @NotNull String descricao, Categoria categoria,
			Usuario vendedor) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
		this.vendedor = vendedor;
	}


	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public LocalDateTime getInstante() {
		return instante;
	}


	public Set<ProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	
	
}
