package br.com.zupacademy.lucaslacerda.mercadolivre.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{
	
	private TokenServiceUsuario tokenServiceCurso;
	private UsuarioRepository repositoryUsuario;
	
	public AutenticacaoViaTokenFilter(TokenServiceUsuario tokenServiceCurso,UsuarioRepository repositoryUsuario) {
		this.tokenServiceCurso = tokenServiceCurso;
		this.repositoryUsuario = repositoryUsuario;

	}
	
	
	
	@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
		 FilterChain filterChain) throws IOException, ServletException {
		
		String token = recuperarToken(request);
		boolean valido = tokenServiceCurso.isTokenValido(token);
		if(valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		
		Long id = tokenServiceCurso.getIdUsuario(token);
		Usuario usuario = repositoryUsuario.findById(id).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());;
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}



	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token==null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7,token.length());
	}
}
