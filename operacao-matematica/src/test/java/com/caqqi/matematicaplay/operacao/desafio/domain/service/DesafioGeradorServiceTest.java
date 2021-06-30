package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.domain.DesafioOperacao;
import com.caqqi.matematicaplay.operacao.desafio.domain.entity.Desafio;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DesafioGeradorServiceTest {

    @Spy
    private Random random;

    private DesafioGeradorService desafioGeradorService;

    @BeforeEach
    public void setUp() {
        desafioGeradorService = new DesafioGeradorServiceImpl(random);
    }

    @ParameterizedTest
    @CsvSource({ "0,+", "1,/", "2,-", "3,*" })
    @DisplayName("Deve gerar um desafio aleatorio entre limites esperados com uma operacao")
    public void deveGerarUmDesafioAleatorioEntreLimitesEsperadosComOperacaoDeAdicao(final Integer index,
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
