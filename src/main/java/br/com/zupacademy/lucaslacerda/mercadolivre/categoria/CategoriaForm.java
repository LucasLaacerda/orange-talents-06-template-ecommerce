package br.com.zupacademy.lucaslacerda.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;
import br.com.zupacademy.lucaslacerda.mercadolivre.validacao.RegistroUnicoValid;
import br.com.zupacademy.lucaslacerda.mercadolivre.validacao.VerificaIdValid;

public class CategoriaForm {

	@NotBlank
	@RegistroUnicoValid(message="Nome informado ja foi cadastrado",entidade = Categoria.class,atributo = "nome")
	private String nome;
	
	@VerificaIdValid(message = "Categoria Mãe inexistente",
					entidade=Categoria.class,campoId = "id",
					permiteNull = "true")
	private Long categoriaMae;
	
	public CategoriaForm(@NotBlank String nome, @NotNull Long categoriaMae) {
		super();
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public String getNome() {
		return nome;
	}

	public Long getCategoriaMae() {
		return categoriaMae;
	}

	
	
	public Categoria toModel(EntityManager manager){
		if(categoriaMae==null) 
			return new Categoria(nome);
		
		return new Categoria(nome,manager.find(Categoria.class, categoriaMae));
	}
	
	
	
}
