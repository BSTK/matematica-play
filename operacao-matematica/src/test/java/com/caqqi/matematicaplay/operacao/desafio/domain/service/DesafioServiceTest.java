package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.DesafioTentativaResposta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class DesafioServiceTest {

    private DesafioService desafioService;

    @BeforeEach
    public void setUp() {
        desafioService = new DesafioServiceImpl();
    }

    @Test
    public void verificaTentativaDeRespostaCorreta() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 20, "+", "usuarioAAB");

        DesafioTentativaResposta desafioTentativaResposta = desafioService.verificarResposta(tentativaRequest);

        then(desafioTentativaResposta.isCorreta()).isTrue();
    }

    @Test
    public void verificaTentativaDeRespostaErrada() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 25, "+", "usuarioAAB");

        DesafioTentativaResposta desafioTentativaResposta = desafioService.verificarResposta(tentativaRequest);

        then(desafioTentativaResposta.isCorreta()).isFalse();
    }
}
