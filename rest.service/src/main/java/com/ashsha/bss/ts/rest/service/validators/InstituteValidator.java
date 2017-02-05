package com.ashsha.bss.ts.rest.service.validators;

import com.ashsha.bss.ts.rest.service.impl.InstituteResource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class InstituteValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz)
    {
        return InstituteResource.class.equals(clazz);
    }

    @Override public void validate(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        InstituteResource instituteResource = (InstituteResource) target;
    }
}
