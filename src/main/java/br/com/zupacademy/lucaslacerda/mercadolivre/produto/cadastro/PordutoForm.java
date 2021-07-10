package br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import br.com.zupacademy.lucaslacerda.mercadolivre.categoria.Categoria;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;
import br.com.zupacademy.lucaslacerda.mercadolivre.validacao.VerificaIdValid;

public class PordutoForm {
	
	@NotBlank
	private String nome;
	@NotNull  @Positive
	private BigDecimal valor;
	@NotNull @Min(value = 0)
	private int quantidade;
	@Size(min=3)
	private Set<ProdutoCaracteristica> caracteristicas;
	@NotBlank @Length(max=1000)
	private String descricao;
	@NotNull
	@VerificaIdValid(message = "Categoria inexistente",
					entidade=Categoria.class,campoId = "id",permiteNull = "false")
	private Long categoria;
	
	public PordutoForm() {
		
	}
	

	public PordutoForm(@NotBlank String nome, @NotNull @DecimalMin("0.0") BigDecimal valor,
			@NotNull @Min(0) int quantidade, @Size(min = 3) Set<ProdutoCaracteristica> caracteristicas,
			@NotBlank @Length(max = 1000) String descricao, @NotNull Long categoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
	}



	public Set<ProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
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




	public Long getCategoria() {
		return categoria;
	}




	public Produto toModel(EntityManager manager, Usuario usuarioLogado) {
		for(ProdutoCaracteristica carac:caracteristicas) {
			manager.persist(carac);
		}
		return new Produto(nome, valor, quantidade, 
				caracteristicas, descricao, manager.find(Categoria.class, categoria), usuarioLogado);
	}

}
