package br.com.zupacademy.lucaslacerda.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class UsuarioForm {

	@NotBlank @Email
	private String login;
	
	@NotBlank @Length(min = 6)
	private String senha;

	public UsuarioForm(@NotBlank @Email String login, @NotBlank String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario toModel() {
		return new Usuario(login,senha);
	}
	
	
}
