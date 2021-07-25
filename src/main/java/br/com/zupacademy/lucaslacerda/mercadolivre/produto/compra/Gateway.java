package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum Gateway {
	pagseguro {
		@Override
		String criaUrlRetorno(Compra compra,
				UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder
					.path("/retorno-pagseguro/{id}")
					.buildAndExpand(compra.getIdcompra()).toString();

			return "pagseguro.com/" + compra.getIdcompra() + "?redirectUrl="
					+ urlRetornoPagseguro;
		}
	},
	paypal {
		@Override
		String criaUrlRetorno(Compra compra,
				UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder
					.path("/retorno-paypal/{id}").buildAndExpand(compra.getIdcompra())
					.toString();

			return "paypal.com/" + compra.getIdcompra() + "?redirectUrl=" + urlRetornoPaypal;
		}
	};
	
	abstract String criaUrlRetorno(Compra compra,
			UriComponentsBuilder uriComponentsBuilder);
}
