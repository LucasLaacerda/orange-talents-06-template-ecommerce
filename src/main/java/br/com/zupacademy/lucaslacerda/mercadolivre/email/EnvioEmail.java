package br.com.zupacademy.lucaslacerda.mercadolivre.email;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta.Pergunta;

public interface EnvioEmail {
	
	void enviaEmailNovaPergunta(Pergunta pergunta);
	
}
