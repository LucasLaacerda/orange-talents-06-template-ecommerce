package br.com.zupacademy.lucaslacerda.mercadolivre.produto.detalhe;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.opiniao.Opiniao;


public class DetalhesProdutoDto {

	private String nome;
	private String descricao;
	private int qtdNotas;
	private double mediaNota;
	private BigDecimal valor;
	private Set<CaracteristicaDto> caracteristicas;
	private Set<OpiniaoDto> opnioes;
	private Set<PerguntasDto> perguntas;
	private Set<ImagensDto> imagens;
	
	
	public DetalhesProdutoDto() {
		
	}
	
	public DetalhesProdutoDto(Produto produto) {
		super();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdNotas = produto.getQtdOpinioes();
		
		this.mediaNota = produto.getOpinioes().stream()
						.mapToInt(Opiniao::getNota).average().orElse(0);
		
		this.valor = (BigDecimal) produto.getValor();
		
		this.caracteristicas =
				produto.getCaracteristicas().stream().map(CaracteristicaDto::new)
				.collect(Collectors.toSet());
		
		this.opnioes = produto.getOpinioes().stream()
						.map(OpiniaoDto::new).collect(Collectors.toSet());
		
		this.perguntas = produto.getPerguntas().stream()
						.map(PerguntasDto::new).collect(Collectors.toSet());
		
		this.imagens =  produto.getImagens().stream()
				.map(ImagensDto::new).collect(Collectors.toSet());
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getQtdNotas() {
		return qtdNotas;
	}

	public double getMediaNota() {
		return mediaNota;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<CaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<OpiniaoDto> getOpnioes() {
		return opnioes;
	}

	public Set<PerguntasDto> getPerguntas() {
		return perguntas;
	}

	public Set<ImagensDto> getImagens() {
		return imagens;
	}

	
}
