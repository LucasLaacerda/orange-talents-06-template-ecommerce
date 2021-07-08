package br.com.zupacademy.lucaslacerda.mercadolivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerificaIdValidator implements ConstraintValidator<VerificaIdValid, Object> {

	 private String atributo;
	 private Class<?> entidade;
	 private String permiteNull;

	
	 @PersistenceContext
	 EntityManager em;
	 
	@Override
	 public void initialize(VerificaIdValid constraintAnnotation) {	
		  atributo = constraintAnnotation.campoId();
	      entidade = constraintAnnotation.entidade();
	      permiteNull = constraintAnnotation.permiteNull();
	 }

	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		if(!verificaNull(value)) 
			return false;
		else if(verificaNull(value) && value==null) 
			return true;
		
	        Query query = em.createQuery("select t from "+this.entidade.getName()+" t where t."+atributo+" = :pValor");
	        query.setParameter("pValor", value);
	        List<?> resultList = query.getResultList();
		
		return (resultList.size()>0);
	}
	
	
	private boolean verificaNull(Object value) {
		
		if(permiteNull.equalsIgnoreCase("false") && value==null) 
			return false;
		return true;	
	}
}
