package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoPagseguroForm implements RetornoGatewatPagamento{

	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagSeguro status;
	
	public PagamentoPagseguroForm(@NotBlank String idTransacao, @NotNull StatusRetornoPagSeguro status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}

	@Override
	public String toString() {
		return "PagamentoPagseguroForm [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	public Transacao toTransacao(Compra compra) {
		
		return new Transacao(status.normaliza(),idTransacao,compra);
	}
	
	
}

