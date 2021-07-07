package br.com.zupacademy.lucaslacerda.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class CadastroUsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Validated UsuarioForm form){
		
		Usuario usuario = form.toModel();	
		usuarioRepository.save(usuario);
		
		return ResponseEntity.ok().build();	
	}
	
	
}
