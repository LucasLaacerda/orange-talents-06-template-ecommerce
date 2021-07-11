package br.com.zupacademy.lucaslacerda.mercadolivre.produto.opiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;

@RestController @RequestMapping("/opiniao")
public class OpiniaoProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid OpiniaoForm form){
		
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Opiniao opiniao = form.toModel(manager,usuarioLogado);	
		manager.persist(opiniao);
		
		return ResponseEntity.ok().build();	
	}
	
}
