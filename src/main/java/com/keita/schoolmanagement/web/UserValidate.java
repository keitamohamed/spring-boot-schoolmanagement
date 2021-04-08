package com.keita.schoolmanagement.web;

import com.keita.schoolmanagement.model.Authenticate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidate implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Authenticate.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Authenticate authenticate = (Authenticate) o;
    }

    public void validate(String o, Errors errors) {

    }
}
