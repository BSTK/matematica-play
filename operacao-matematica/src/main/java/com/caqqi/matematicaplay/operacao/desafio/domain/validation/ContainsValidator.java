package com.caqqi.matematicaplay.operacao.desafio.domain.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ContainsValidator implements ConstraintValidator<Contains, String> {

    private String[] range;

    @Override
    public void initialize(Contains annotation) {
        range = annotation.range();
    }

    @Override
    public boolean isValid(final String valor,
                           final ConstraintValidatorContext context) {
        if (!StringUtils.hasLength(valor)) {
            return false;
        }

        if (Objects.isNull(range) || range.length == 0) {
            return false;
        }

        for (String item : range) {
            if (item.equals(valor)) {
                return true;
            }
        }

        return false;
    }
}
