package br.com.zupacademy.lucaslacerda.mercadolivre.email;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra.Compra;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta.Pergunta;

public interface EnvioEmail {
	
	void enviaEmailNovaPergunta(Pergunta pergunta);
	
	void enviaEmailInteresseDeCompra(Compra compra);

	void enviaEmailCompraNaoProcessada(Compra compra);
	
}
