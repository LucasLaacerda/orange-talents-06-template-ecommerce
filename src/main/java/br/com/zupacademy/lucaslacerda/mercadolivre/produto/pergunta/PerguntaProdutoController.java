package br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucaslacerda.mercadolivre.email.Email;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;

@RestController
public class PerguntaProdutoController {

	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private Email mail;
	
	@PostMapping("/produtos/pergunta")
	@Transactional
	public ResponseEntity<String> cadastrar(@RequestBody @Valid PerguntaForm form){
		
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Pergunta pergunta = form.toModel(manager,usuarioLogado);	
		manager.persist(pergunta);
		
		encaminhaEmailVendedor(pergunta);
		
		return ResponseEntity.ok(form.toString());
	}
	
	
	private void encaminhaEmailVendedor(Pergunta pergunta) {
		mail.enviaEmailNovaPergunta(pergunta);
	}
	
}
