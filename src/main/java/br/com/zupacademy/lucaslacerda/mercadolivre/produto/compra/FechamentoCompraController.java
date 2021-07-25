package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FechamentoCompraController {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Autowired
	private EventosNovaCompra eventosNovaCompra;
	
	
	@PostMapping(value="/retorno-pagseguro/{id}")
	@Transactional
	public String pagamentoPagSeguro(@PathVariable("id") Long idCompra,@RequestBody PagamentoPagseguroForm form) {
		return processa(idCompra, form);
	}
	
	
	@PostMapping(value="/retorno-paypal/{id}")
	@Transactional
	public String pagamentoPaypal(@PathVariable("id") Long idCompra,@RequestBody PagamentoPaypalForm form) {
		
		return processa(idCompra, form);
	}
	
	
	private String processa(Long idCompra, RetornoGatewatPagamento retornoGatewayPagamento) {
		
		Compra compra = Optional.ofNullable(manager.find(Compra.class,idCompra))
						.orElseThrow(()-> 					
						new ResponseStatusException(
								HttpStatus.NOT_FOUND, 
								"Compra não encontrado."));
		
		compra.realizaPagamento(retornoGatewayPagamento);
		manager.merge(compra);
		
		eventosNovaCompra.processa(compra);
	
		
		return compra.toString();

	}
	
}
