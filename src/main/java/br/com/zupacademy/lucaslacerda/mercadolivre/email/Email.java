package br.com.zupacademy.lucaslacerda.mercadolivre.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra.Compra;
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

	@Override
	public void enviaEmailInteresseDeCompra(Compra compra) {
		System.out.println("Enviar para: "+compra.getProduto().getVendedor().getUsername());
		System.out.println("\nUsuario: '"+compra.getCliente().getUsername()
							+"' \nEsta interessado em comprar o seu produto: "+compra.getProduto().getNome()
							+"\nQuantia requisitada: "
							+compra.getQuantidade());
		
	}
	
	@Override
	public void enviaEmailCompraNaoProcessada(Compra compra) {
		
		System.out.println("Não foi possivel processar a compra: "+compra.getId());
		
	}

	
	
}
