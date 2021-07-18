package br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.imagem.ImagemForm;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.imagem.UploaderFake;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produto")
public class CadastroProdutoController {

	//SecurityContextHolder.getContext().getAuthentication().getAuthorities().

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UploaderFake uploaderFake;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PordutoForm form){
		
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Produto produto = form.toModel(manager,usuarioLogado);	
		manager.persist(produto);
		
		return ResponseEntity.ok().build();	
	}
	
	@PostMapping(value="/{id}/imagens")
	@Transactional
	public ResponseEntity<?> incluiImagem(@PathVariable("id") Long id,@Valid ImagemForm form){
	
		
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(manager.find(Produto.class, id)==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		Produto produto = manager.find(Produto.class, id);

		if(!produto.verificaDono(usuarioLogado)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploaderFake.envia(form.getImagens());
		produto.relacionaImagens(links);
		
		manager.merge(produto);
		return ResponseEntity.ok().build();	
	}
}
