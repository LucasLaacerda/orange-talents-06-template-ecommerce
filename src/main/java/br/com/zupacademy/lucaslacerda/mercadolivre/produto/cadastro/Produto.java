package br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.lucaslacerda.mercadolivre.categoria.Categoria;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.imagem.ImagemProduto;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta.Pergunta;
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
	private Integer quantidade;
	
	 
	@OneToMany(mappedBy = "produto",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@Size(min=3)
	private Set<ProdutoCaracteristica> caracteristicas = new HashSet<>();
	
	@NotNull
	@Column(nullable=false)
	private String descricao;
	
	@ManyToOne
	private Categoria categoria;

	@ManyToOne
	private Usuario vendedor;
	
	private LocalDateTime instante = LocalDateTime.now();

	@OneToMany(mappedBy = "produto",fetch = FetchType.EAGER)
	private Set<Opiniao> opinioes = new HashSet<Opiniao>();
	
	@OneToMany(mappedBy = "produto",fetch = FetchType.EAGER)
	private Set<Pergunta> perguntas = new HashSet<Pergunta>();
	
	@OneToMany(mappedBy = "produto",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	private Set<ImagemProduto> imagens = new HashSet<ImagemProduto>();
	
	@Deprecated
	public Produto() {
		
	}

	public Produto(@NotNull String nome, @NotNull BigDecimal valor, @NotNull int quantidade,
			@Size(min = 3) Collection<CaracteristicaForm> caracteristicas, @NotNull String descricao, Categoria categoria,
			Usuario vendedor) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas.addAll(caracteristicas
				.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void relacionaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = 
		  links.stream().map(link -> new ImagemProduto(this,link))
		  .collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}

	public boolean verificaDono(Usuario usuarioLogado) {	
		return this.vendedor.getId().equals(usuarioLogado.getId());
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}

	public Set<Pergunta> getPerguntas() {
		return perguntas;
	}
	
	public int getQtdOpinioes() {
		return opinioes.size();
	}
	
	
}
