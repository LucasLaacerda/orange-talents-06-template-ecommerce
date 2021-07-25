package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PagamentoPaypalForm implements RetornoGatewatPagamento{

	@Min(0)
	@Max(1)
	private int status;
	
	@NotBlank
	private String idTransacao;
	
	@Deprecated
	public PagamentoPaypalForm() {
		
	}

	public PagamentoPaypalForm(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
		super();
		this.status = status;
		this.idTransacao = idTransacao;
	}

	public int getStatus() {
		return status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}
	
	public Transacao toTransacao(Compra compra) {
		
		return new Transacao(this.status==0?
				StatusTransacao.ERRO : StatusTransacao.SUCESSO,
				idTransacao,compra);
	}
	
	
}
