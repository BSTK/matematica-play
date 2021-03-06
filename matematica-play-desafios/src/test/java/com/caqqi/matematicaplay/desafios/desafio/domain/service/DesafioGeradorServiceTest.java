package com.caqqi.matematicaplay.desafios.desafio.domain.service;

import com.caqqi.matematicaplay.desafios.domain.Desafio;
import com.caqqi.matematicaplay.desafios.domain.DesafioOperacao;
import com.caqqi.matematicaplay.desafios.domain.service.DesafioGeradorService;
import com.caqqi.matematicaplay.desafios.domain.service.DesafioGeradorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DesafioGeradorServiceTest {

    @Spy
    private SecureRandom random;

    private DesafioGeradorService desafioGeradorService;

    @BeforeEach
    void setUp() {
        desafioGeradorService = new DesafioGeradorServiceImpl(random);
    }

    @ParameterizedTest
    @CsvSource({ "0,+", "1,/", "2,-", "3,*" })
    @DisplayName("Deve gerar um desafio aleatorio entre limites esperados com uma operacao")
    void deveGerarUmDesafioAleatorioEntreLimitesEsperadosComOperacaoDeAdicao(final Integer index,
                                                                                    final String operacao) {
        given(random.nextInt(89)).willReturn(20, 30);
        given(random.nextInt(DesafioOperacao.values().length)).willReturn(index);

        Desafio desafio = desafioGeradorService.gerarDesafioAleatorio();

        Assertions.assertThat(desafio.getFatorA()).isEqualTo(31);
        Assertions.assertThat(desafio.getFatorB()).isEqualTo(41);
        Assertions.assertThat(desafio.getOperacao()).isEqualTo(operacao);
        Assertions.assertThat(desafio.getAlternativas()).hasSize(4);

        final int resultadoCorreto = DesafioOperacao.of(operacao)
            .execute(desafio.getFatorA(), desafio.getFatorB());

        Assertions.assertThat(desafio.getAlternativas()).contains(resultadoCorreto);
    }

}
