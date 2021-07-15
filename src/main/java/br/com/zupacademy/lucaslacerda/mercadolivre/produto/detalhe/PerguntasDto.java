package br.com.zupacademy.lucaslacerda.mercadolivre.produto.detalhe;


import br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;

public class PerguntasDto {

	private String titulo;
	private String remetente;
	
	public PerguntasDto(Pergunta pergunta) {
		super();
		this.titulo = pergunta.getTitulo();
		this.remetente = pergunta.getRemetente().getUsername();
	}
	
	public String getTitulo() {
		return titulo;
	}
	public String getRemetente() {
		return remetente;
	}
	
	
	
}
