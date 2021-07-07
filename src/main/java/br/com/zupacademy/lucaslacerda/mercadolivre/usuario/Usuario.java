package br.com.zupacademy.lucaslacerda.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sun.istack.NotNull;

@Entity
public class Usuario {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @Email
	@Column(nullable=false)
	private String login;
	
	@NotNull
	@Column(nullable=false)
	private String senha;

	@NotNull @PastOrPresent
	private LocalDateTime instante = LocalDateTime.now();
	
	public Usuario() {
		
	}
	
	public Usuario(@Email String login, String senha) {
		super();
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	
	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
	
	
	
	
}
