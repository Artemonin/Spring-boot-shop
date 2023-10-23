package com.pitonov.myshop.validator;

import com.pitonov.myshop.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description","error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price","error.not_empty");

        if(product.getName().length()<1){
            errors.rejectValue("name", "product.error.name.less_2");
        }

        if (product.getName().length()>32){
            errors.rejectValue("name", "product.error.name.over_32");
        }

    }
}
