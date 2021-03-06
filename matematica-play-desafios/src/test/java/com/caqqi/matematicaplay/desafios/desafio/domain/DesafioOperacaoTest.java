package com.caqqi.matematicaplay.desafios.desafio.domain;

import com.caqqi.matematicaplay.desafios.domain.DesafioOperacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DesafioOperacaoTest {

    @Test
    @DisplayName("Deve retornar uma operacao valida")
    void deveRetornarUmaOperacaoValida() {
        Assertions.assertThat(DesafioOperacao.of("+")).isEqualTo(DesafioOperacao.ADICAO);
        Assertions.assertThat(DesafioOperacao.of("/")).isEqualTo(DesafioOperacao.DIVISAO);
        Assertions.assertThat(DesafioOperacao.of("-")).isEqualTo(DesafioOperacao.SUBTRACAO);
        Assertions.assertThat(DesafioOperacao.of("*")).isEqualTo(DesafioOperacao.MULTIPLICACAO);
    }

    @Test
    @DisplayName("Deve retornar uma operacao valida")
    void deveLancarExcesaoDadoUmaOperacaoInvalida() {
        String menssagemErro = "Operador inválido ( %s ).";
        Assertions.assertThatThrownBy(() -> DesafioOperacao.of("@"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(menssagemErro, "@"));

        Assertions.assertThatThrownBy(() -> DesafioOperacao.of("#"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(menssagemErro, "#"));

        Assertions.assertThatThrownBy(() -> DesafioOperacao.of("$"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(menssagemErro, "$"));
    }
}
