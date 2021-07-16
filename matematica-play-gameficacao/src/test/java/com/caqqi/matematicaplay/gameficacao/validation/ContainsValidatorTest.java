package com.caqqi.matematicaplay.gameficacao.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContainsValidatorTest {

    private ContainsValidator validator = new ContainsValidator();

    @BeforeEach
    void setUp() {
        configuraWhenContainsAnnotation(new String[]{ "+", "-", "*", "/" });
    }

    @Test
    void deveRetornarTrueParaUmaStringComOperacaoValida() {
        boolean stringSomaValida = validator.isValid("+", null);
        boolean stringDivisaoValida = validator.isValid("/", null);
        boolean stringSubtracaoValida = validator.isValid("-", null);
        boolean stringMultiplicacaoValida = validator.isValid("*", null);

        Assertions.assertThat(stringSomaValida).isTrue();
        Assertions.assertThat(stringDivisaoValida).isTrue();
        Assertions.assertThat(stringSubtracaoValida).isTrue();
        Assertions.assertThat(stringMultiplicacaoValida).isTrue();
    }

    @Test
    void deveRetornarFalseParaUmaStringComOperacaoInvalida() {
        boolean stringInalidaA = validator.isValid("#", null);
        boolean stringInalidaB = validator.isValid("%", null);
        boolean stringInalidaC = validator.isValid("@", null);
        boolean stringInalidaD = validator.isValid(")", null);

        Assertions.assertThat(stringInalidaA).isFalse();
        Assertions.assertThat(stringInalidaB).isFalse();
        Assertions.assertThat(stringInalidaC).isFalse();
        Assertions.assertThat(stringInalidaD).isFalse();
    }

    @Test
    void deveRetornarFalseParaUmaStringComOperacaoVazia() {
        boolean stringOperacaoVazia = validator.isValid("", null);
        Assertions.assertThat(stringOperacaoVazia).isFalse();
    }

    @Test
    void deveRetornarFalseParaUmaStringComOperacaoNula() {
        boolean stringOperacaoNula = validator.isValid(null, null);
        Assertions.assertThat(stringOperacaoNula).isFalse();
    }

    @Test
    void deveRetornarFalseParaRangeNulo() {
        configuraWhenContainsAnnotation(null);

        boolean stringOperacaoNula = validator.isValid("+", null);
        Assertions.assertThat(stringOperacaoNula).isFalse();
    }

    @Test
    void deveRetornarFalseParaRangeVazio() {
        configuraWhenContainsAnnotation(new String[]{});

        boolean stringOperacaoNula = validator.isValid("+", null);
        Assertions.assertThat(stringOperacaoNula).isFalse();
    }

    private void configuraWhenContainsAnnotation(final String[] range) {
        Contains containsMock = mock(Contains.class);
        when(containsMock.range()).thenReturn(range);
        validator.initialize(containsMock);
    }

}
