package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.zupacademy.lucaslacerda.mercadolivre.email.Email;

public class EventosNovaCompra {

	@Autowired
	private Set<EventoCompraSucesso> eventosComprasSucesso;
	private Email mail;

	
	public void processa(Compra compra) {
		
		if(compra.processadaComSucesso()) {		
			
			eventosComprasSucesso.forEach(evento -> evento.processa(compra));
	
		}else {
			mail.enviaEmailCompraNaoProcessada(compra);
		}
	}

}
