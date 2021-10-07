package br.com.zupacademy.henriquecesar.mercadolivre.form.validator;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {
    
    Class<?> entityClass;
    String columnId;
    boolean required;
    
    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public void initialize(ExisteId constraintAnnotation) {
        entityClass = constraintAnnotation.entityClass();
        columnId = constraintAnnotation.columnId();
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) && !required) {
            return true;
        }
        
        String sql = "SELECT 1 FROM class WHERE columnId = :value"
                .replace("class", entityClass.getName())
                .replace("columnId", columnId);
        
        Query query = manager.createQuery(sql);
        query.setParameter("value", value);
        
        List<?> result = query.getResultList();
        return !result.isEmpty();
    }

}
