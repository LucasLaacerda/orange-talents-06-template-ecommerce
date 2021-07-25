package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

public class Ranking implements EventoCompraSucesso{

	@Override
	public void processa(Compra compra) {
		System.out.println("Post sistema Ranking: Compra: "+
		compra.getId()
		+" e Vendedor: "+compra.getProduto().getVendedor().getId());
	}

}
