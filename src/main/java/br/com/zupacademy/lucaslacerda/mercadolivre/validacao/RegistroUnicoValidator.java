package br.com.zupacademy.lucaslacerda.mercadolivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.persistence.Query;

public class RegistroUnicoValidator implements ConstraintValidator<RegistroUnicoValid, String> {

	
	 private String value;
	 private Class<?> entidade;
	 private String atributo;
	
	 @PersistenceContext
	 EntityManager em;
	 
	@Override
	 public void initialize(RegistroUnicoValid constraintAnnotation) {
			
	      this.value = constraintAnnotation.value();
	      this.entidade = constraintAnnotation.entidade();
	      this.atributo = constraintAnnotation.atributo();
	 }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
	        Query query = em.createQuery("select t from "+this.entidade.getName()+" t where t."+this.atributo+" = :pValor");
	        query.setParameter("pValor", value);
	        List<?> resultList = query.getResultList();
		
		return !(resultList.size()>0);
	}

}
