package br.com.zupacademy.lucaslacerda.mercadolivre.produto.compra;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private StatusTransacao status;
	@NotBlank
	private String idTransacaoGateway;
	private LocalDateTime instante;
	private @ManyToOne @NotNull @Valid Compra compra;
	
	@Deprecated
	public Transacao() {
	
	}
	
	public Transacao(StatusTransacao status, @NotBlank String idTransacaoGateway,@NotNull @Valid Compra compra) {
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.compra = compra;
		this.instante = LocalDateTime.now();
	}

	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.SUCESSO);
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compra == null) ? 0 : compra.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
		result = prime * result + ((instante == null) ? 0 : instante.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (compra == null) {
			if (other.compra != null)
				return false;
		} else if (!compra.equals(other.compra))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idTransacaoGateway == null) {
			if (other.idTransacaoGateway != null)
				return false;
		} else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
			return false;
		if (instante == null) {
			if (other.instante != null)
				return false;
		} else if (!instante.equals(other.instante))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public String getIdTransacaoGateway() {
		return idTransacaoGateway;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public Compra getCompra() {
		return compra;
	}
	
	

}
