package br.com.zupacademy.lucaslacerda.mercadolivre.produto.detalhe;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;

@RestController
public class ProdutoDetalhesController {

	@PersistenceContext
	private EntityManager manager;
	
	
	@GetMapping("/produtos/{id}")
	public ResponseEntity<DetalhesProdutoDto> detalhar(@PathVariable Long id) {
		
		if(manager.find(Produto.class, id)==null) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(new DetalhesProdutoDto(manager.find(Produto.class, id)));	
		
	}
	
	
	
}
