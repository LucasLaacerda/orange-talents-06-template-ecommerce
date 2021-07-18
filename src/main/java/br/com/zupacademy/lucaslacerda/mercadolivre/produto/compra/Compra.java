package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.ProdutoRepository;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;

@Entity
public class Compra {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull @Valid
	private Produto produto;
	
	@ManyToOne
	@NotNull @Valid
	private Usuario cliente;
	
	@NotNull
	@Column(nullable=false)
	private Integer quantidade;
	
	@NotNull
	@Column(nullable=false)
	private BigDecimal valor;
	
	@NotNull
	@Column(nullable=false)
	private UUID idcompra;
	
	@Enumerated
	@NotNull @Column(nullable=false)
	private Gateway gateway;
	
	@Enumerated
	@NotNull @Column(nullable=false)
	private Status status;

	@Deprecated
	public Compra() {
		
		
	}
	
	public Compra(@NotNull @Valid Produto produto, @NotNull @Valid Usuario cliente, @NotNull Integer quantidade,
			@NotNull Gateway gateway, ProdutoRepository produtoRepository) {
		super();
		this.produto = produto;
		this.cliente = cliente;
		this.quantidade = quantidade;
		this.valor = produto.getValor().multiply(new BigDecimal(quantidade));
		this.idcompra = UUID.randomUUID();
		this.gateway = gateway;
		this.status = status.INICIADA;
		baixaNoEstoque(produtoRepository);
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public UUID getIdcompra() {
		return idcompra;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public Status getStatus() {
		return status;
	}
	
	private void baixaNoEstoque(ProdutoRepository produtoRepository) {
		this.produto.atualizaEstoque(this.quantidade, "baixa");
		produtoRepository.saveAndFlush(this.produto);
	}
	
	
}
