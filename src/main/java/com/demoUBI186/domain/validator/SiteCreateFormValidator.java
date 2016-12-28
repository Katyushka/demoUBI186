package com.demoUBI186.domain.validator;

import com.demoUBI186.domain.SiteCreateForm;
import com.demoUBI186.domain.SiteCreateForm;
import com.demoUBI186.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

/**
 * Created by ekaterina on 13.12.2016.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

@Component
public class SiteCreateFormValidator implements org.springframework.validation.Validator{

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteCreateFormValidator.class);

    @Autowired
    private SiteService sitesService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SiteCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        SiteCreateForm form = (SiteCreateForm) target;

    }
}
