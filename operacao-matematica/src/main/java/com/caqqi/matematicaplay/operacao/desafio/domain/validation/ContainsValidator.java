package com.caqqi.matematicaplay.operacao.desafio.domain.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContainsValidator implements ConstraintValidator<Contains, String> {

    @Override
    public boolean isValid(final String valor,
                           final ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.hasLength(valor)) {
            return false;
        }

        final String[] range = constraintValidatorContext.unwrap(Contains.class).range();

        for (String item : range) {
            if (item.equals(valor)) {
                return true;
            }
        }

        return false;
    }
}
