package br.com.zupacademy.lucaslacerda.mercadolivre.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenServiceUsuario tokenService;
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configuracoes de autorizacao url e ets
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
	
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/usuario").permitAll()
		.antMatchers(HttpMethod.POST,"/compra/**").permitAll()
		.antMatchers(HttpMethod.POST,"/retorno-paypal/**").permitAll()
		.antMatchers(HttpMethod.POST,"/auth").permitAll()
		.antMatchers(HttpMethod.GET,"/actuator/**").permitAll()
		//.antMatchers(HttpMethod.DELETE,"/topicos/*").access("hasRole('MODERADOR') and hasRole('ALUNO')") 
		//.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService,repositoryUsuario), UsernamePasswordAuthenticationFilter.class);
	
	}
	
	//Configuracoes de recursos estaticos(js,css,imagens,etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		  web.ignoring()
	        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
		
	}

}
