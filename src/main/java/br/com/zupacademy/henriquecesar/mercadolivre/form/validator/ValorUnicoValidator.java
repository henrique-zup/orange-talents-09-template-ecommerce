package br.com.zupacademy.henriquecesar.mercadolivre.form.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {
    
    private String field;
    private Class<?> entityClass; 
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        field = constraintAnnotation.field();
        entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
       
        String sql = "select 1 from class where field = :value"
                .replace("class", entityClass.getName())
                .replace("field", field);
        
        Query query = entityManager.createQuery(sql);
        query.setParameter("value", value);
        
        List<?> result = query.getResultList();
        return result.isEmpty();
    }

}
