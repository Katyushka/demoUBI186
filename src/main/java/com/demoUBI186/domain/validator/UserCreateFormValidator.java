package com.demoUBI186.domain.validator;

import com.demoUBI186.domain.UserCreateForm;
import com.demoUBI186.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import javax.validation.Validator;

/**
 * Created by ekaterina on 13.12.2016.
 */

@Component
public class UserCreateFormValidator implements org.springframework.validation.Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz){
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors){
        LOGGER.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm)target;
        validatePassword(errors, form);
        validateLogin(errors, form);
    }

    private void validateLogin(Errors errors, UserCreateForm form) {
        if (userService.getUserByLogin(form.getLogin()) != null){
            errors.reject("User with this name already exist");
        }
    }

    private void validatePassword(Errors errors, UserCreateForm form) {

    }

}

