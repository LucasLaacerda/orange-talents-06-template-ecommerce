package br.com.zupacademy.lucaslacerda.mercadolivre.validacao;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
 

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = RegistroUnicoValidator.class)
public @interface RegistroUnicoValid {
	
	String message() default ""; 
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {}; 
    String value() default "";
    Class<?> entidade();
    String atributo() default "";
    
}
