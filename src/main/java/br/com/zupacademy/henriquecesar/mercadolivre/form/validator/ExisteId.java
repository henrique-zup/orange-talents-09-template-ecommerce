package br.com.zupacademy.henriquecesar.mercadolivre.form.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ExisteIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ExisteId {
   
    String message() default "Id não existe no banco de dados";
    
    Class<?>[] groups() default { };
    
    Class<? extends Payload>[] payload() default { };
    
    Class<?> entityClass();
    
    String columnId() default "id";
    
    boolean required() default true;

}
