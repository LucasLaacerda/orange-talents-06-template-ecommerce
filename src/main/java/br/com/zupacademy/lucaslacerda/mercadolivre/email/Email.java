package br.com.zupacademy.lucaslacerda.mercadolivre.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta.Pergunta;

@Component
@Primary
public class Email implements EnvioEmail{

	@Override
	public void enviaEmailNovaPergunta(Pergunta pergunta) {
		System.out.println("Enviar para: "+pergunta.getProduto().getVendedor().getUsername());
		System.out.println("\nNova pergunta recebida: '"+pergunta.getTitulo()
							+"' \n DE: "+pergunta.getRemetente().getUsername()+"\n Produto:"
							+pergunta.getProduto().getNome());
		
	}

	
	
}
