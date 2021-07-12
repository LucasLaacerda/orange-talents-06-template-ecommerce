package br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;
import br.com.zupacademy.lucaslacerda.mercadolivre.validacao.VerificaIdValid;

@Component
public class PerguntaForm {

	@NotBlank
	private String titulo;
	
	@VerificaIdValid(message = "Produto inexistente",entidade=Produto.class,campoId = "id", permiteNull = "false")
	private Long idproduto;
	
	public PerguntaForm() {
		
	}	

	public PerguntaForm(@NotBlank String titulo, Long idproduto) {
		super();
		this.titulo = titulo;
		this.idproduto = idproduto;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public Long getIdproduto() {
		return idproduto;
	}

	public Pergunta toModel(EntityManager manager, Usuario usuarioLogado) {
		return new Pergunta(titulo, manager.find(Produto.class, idproduto), usuarioLogado);
	}

	@Override
	public String toString() {
		return "Pergunta [titulo=" + titulo + "]";
	}
	
	
	
}
