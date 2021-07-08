package br.com.zupacademy.lucaslacerda.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	 @PersistenceContext
	 private EntityManager manager;
	
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Validated CategoriaForm form){
		
		Categoria categoria = form.toModel(manager);	
		manager.persist(categoria);
		
		return ResponseEntity.ok().build();	
	}
	

}
