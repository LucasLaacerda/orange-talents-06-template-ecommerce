package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import java.util.Optional;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.ProdutoRepository;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;
import br.com.zupacademy.lucaslacerda.mercadolivre.validacao.VerificaIdValid;

public class CompraForm {

	@NotNull @VerificaIdValid(message = "Produto inexistente",entidade=Produto.class,campoId = "id", permiteNull = "false")
	private Long idProduto;
	
	@NotNull @Positive
	private Integer quantidade;
	
	@Enumerated
	@NotNull
	private Gateway gateway;

	@Deprecated
	public CompraForm() {
		
	}
	
	
	public CompraForm(@NotNull Long idProduto, @NotNull @Positive Integer quantidade, @NotNull Gateway gateway) {
		super();
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.gateway = gateway;
	}


	public Long getIdProduto() {
		return idProduto;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public Gateway getGateway() {
		return gateway;
	}


	public Compra toModel(Produto produto, Usuario usuarioLogado, 
						Integer qtd, CompraRepository compraRepository, Gateway gt, ProdutoRepository produtoRepository) {
		
		if(!produto.verificaEstoque(qtd)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Produto sem estoque para a qauntia informada!");
		}
		
		return new Compra(produto, usuarioLogado,qtd, gt,produtoRepository);
	}
		
	
}
