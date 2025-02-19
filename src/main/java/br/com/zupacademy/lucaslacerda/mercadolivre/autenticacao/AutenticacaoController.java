package br.com.zupacademy.lucaslacerda.mercadolivre.autenticacao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zupacademy.lucaslacerda.mercadolivre.config.security.TokenServiceUsuario;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenServiceUsuario tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();

		try {
			Authentication authentication = authenticationManager.authenticate(dadosLogin);	
			
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		
	}
	
}
