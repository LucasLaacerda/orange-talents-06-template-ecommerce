package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

public enum StatusRetornoPagSeguro {
	SUCESSO,
	ERRO;

	public StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.ERRO;
	}
}
