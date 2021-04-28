package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.Desafio;
import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.DesafioOperacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.BDDAssertions.then;
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

    @Test
    public void deveGerarUmDesafioAleatorioEntreLimitesEsperadosComOperacaoDeAdicao() {
        given(random.nextInt(89)).willReturn(20, 30);
        given(random.nextInt(DesafioOperacao.values().length)).willReturn(0);

        Desafio desafio = desafioGeradorService.gerarDesafioRandomico();

        then(desafio).isEqualTo(new Desafio(31, 41, "+"));
    }
}
