package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaFiscal implements EventoCompraSucesso{
	
	@Override
	public void processa(Compra compra) {
		System.out.println("Post sistema nota fiscal: Compra: "+
		compra.getId()
		+" e Comprador: "+compra.getCliente().getId());
	}
	
}
