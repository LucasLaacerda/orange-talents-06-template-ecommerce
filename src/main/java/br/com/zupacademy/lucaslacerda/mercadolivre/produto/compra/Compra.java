package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

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
	private String idcompra;
	
	@Enumerated
	@NotNull @Column(nullable=false)
	private Gateway gateway;
	
	@Enumerated
	@NotNull @Column(nullable=false)
	private Status status;

	@OneToMany(mappedBy = "compra",cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();
	
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
		this.idcompra = UUID.randomUUID().toString();
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

	public String getIdcompra() {
		return idcompra;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public Status getStatus() {
		return status;
	}
	
	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

	private void baixaNoEstoque(ProdutoRepository produtoRepository) {
		this.produto.atualizaEstoque(this.quantidade, "baixa");
		produtoRepository.saveAndFlush(this.produto);
	}
	
	public String urlRedirecionamento(
			UriComponentsBuilder uriComponentsBuilder) {
		return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
	}

	public Long getId() {
		return id;
	}

	public void realizaPagamento(@Valid RetornoGatewatPagamento form) {
		
		Transacao transacao = form.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(transacao),"Transação ja existente: "+transacao);
		
		
		Assert.isTrue(transacoesConcluidaComSucesso().isEmpty(),"Essa compra ja foi concluida com sucesso.");
		
		this.transacoes.add(transacao);
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidaComSucesso().isEmpty();
	}

	private Set<Transacao> transacoesConcluidaComSucesso(){
		Set<Transacao> transacoes = this.transacoes.stream().filter(Transacao :: concluidaComSucesso)
				.collect(Collectors.toSet());
			return transacoes;
	}

	@Override
	public String toString() {
		return "Compra [produto=" + produto + ", cliente=" + cliente + ", quantidade=" + quantidade + ", valor=" + valor
				+ ", idcompra=" + idcompra + ", gateway=" + gateway + ", status=" + status + ", transacoes="
				+ transacoes + "]";
	}
	
}
