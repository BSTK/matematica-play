package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.DesafioTentativaResposta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class DesafioServiceTest {

    private DesafioService desafioService;

    @BeforeEach
    public void setUp() {
        desafioService = new DesafioServiceImpl();
    }

    @Test
    @DisplayName("Verifica tentativa de resposta correta operacao soma")
    public void verificaTentativaDeRespostaCorretaOperacaoSoma() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 20, "+", "UsuarioAAB");

        executaValidacaoCorreta(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta errada operacao soma")
    public void verificaTentativaDeRespostaErradaOperacaoSoma() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 25, "+", "UsuarioAAB");

        executaValidacaoErrada(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta correta operacao subtração")
    public void verificaTentativaDeRespostaCorretaOperacaoSubtracao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 0, "-", "UsuarioAAB");

        executaValidacaoCorreta(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta errada operacao subtração")
    public void verificaTentativaDeRespostaErradaOperacaoSubtracao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 25, "-", "UsuarioAAB");

        executaValidacaoErrada(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta correta operacao divisao")
    public void verificaTentativaDeRespostaCorretaOperacaoDivisao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 1, "/", "UsuarioAAB");

        executaValidacaoCorreta(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta errada operacao divisao")
    public void verificaTentativaDeRespostaErradaOperacaoDivisao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 0, "/", "UsuarioAAB");

        executaValidacaoErrada(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta correta operacao multiplicacao")
    public void verificaTentativaDeRespostaCorretaOperacaoMultiplicacao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 100, "*", "UsuarioAAB");

        executaValidacaoCorreta(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta errada operacao multiplicacao")
    public void verificaTentativaDeRespostaErradaOperacaoMultiplicacao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 99, "*", "UsuarioAAB");

        executaValidacaoErrada(tentativaRequest);
    }

    private void executaValidacaoCorreta(final DesafioTentativaRespostaRequest request) {
        DesafioTentativaResposta desafioTentativaResposta = desafioService.verificarResposta(request);
        then(desafioTentativaResposta.isCorreta()).isTrue();
    }

    private void executaValidacaoErrada(final DesafioTentativaRespostaRequest request) {
        DesafioTentativaResposta desafioTentativaResposta = desafioService.verificarResposta(request);
        then(desafioTentativaResposta.isCorreta()).isFalse();
    }
}
