package br.com.zupacademy.lucaslacerda.mercadolivre.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class CadastroUsuarioController {

	 @PersistenceContext
	 private EntityManager manager;
	
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm form){
		
		Usuario usuario = form.toModel();	
		manager.persist(usuario);
		
		return ResponseEntity.ok().build();	
	}
	
	
}
