package br.com.zupacademy.henriquecesar.mercadolivre.form.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ValorUnicoValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {
    
    String message() default "Valor já cadastrado no banco de dados";
    
    Class<?>[] groups() default { };
    
    Class<? extends Payload>[] payload() default { };
    
    String field();
    
    Class<?> entityClass();

}
