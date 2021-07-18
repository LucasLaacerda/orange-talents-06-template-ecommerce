package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import br.com.zupacademy.lucaslacerda.mercadolivre.email.Email;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.Produto;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.cadastro.ProdutoRepository;
import br.com.zupacademy.lucaslacerda.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.lucaslacerda.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/compra")
public class CompraProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private Email mail;

	@PostMapping
	@Transactional
	@ResponseStatus(value = HttpStatus.MOVED_TEMPORARILY)
	public String realizaCompra(@RequestBody @Valid CompraForm form){
		
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Produto produto = produtoRepository.findById(form.getIdProduto()).get();
		//Temos @VerificaID no form para nao chegar produto null
			
		Compra compra = form.toModel(produto,usuarioLogado,
							form.getQuantidade(),compraRepository,form.getGateway(),produtoRepository);
		compraRepository.save(compra);
			
		encaminhaEmailVendedor(compra);
		return montaLinkRedirect(form.getGateway(), compra);
	}
	
	private String montaLinkRedirect(Gateway gateway,Compra compra) {
		
		
		if(gateway==Gateway.PAGSEGURO)
			return "pagseguro.com?returnId="+compra.getIdcompra()+"&redirectUrl=urlRetornoAppPosPagamento";
		else 
			return "paypal.com?buyerId="+compra.getIdcompra()+"&redirectUrl=urlRetornoAppPosPagamento";
		
	}
	
	private void encaminhaEmailVendedor(Compra compra) {
		mail.enviaEmailInteresseDeCompra(compra);
	}
	
	
	
}
