package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.domain.entity.DesafioTentativaResposta;
import com.caqqi.matematicaplay.operacao.desafio.domain.repository.DesafioTentativaRespostaRepository;
import com.caqqi.matematicaplay.operacao.usuario.domain.entity.Usuario;
import com.caqqi.matematicaplay.operacao.usuario.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DesafioServiceTest {

    private DesafioService desafioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private DesafioTentativaRespostaRepository desafioTentativaRespostaRepository;

    @BeforeEach
    public void setUp() {
        desafioService = new DesafioServiceImpl(
            usuarioRepository,
            desafioTentativaRespostaRepository
        );

        given(desafioTentativaRespostaRepository.save(any()))
            .will(returnsFirstArg());
    }

    @Test
    @DisplayName("Verifica usuário já existente")
    public void verificaUsuarioJaExistente() {
        Usuario usuario = new Usuario("USUARIO_AA");

        given(usuarioRepository.buscarPorApelido("USUARIO_AA"))
            .willReturn(Optional.of(usuario));

        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 30, "+", "USUARIO_AA");

        DesafioTentativaResposta desafioTentativaResposta = desafioService.verificarResposta(tentativaRequest);

        then(desafioTentativaResposta.isCorreta()).isFalse();
        then(desafioTentativaResposta.getUsuario()).isEqualTo(usuario);

        verify(usuarioRepository, never()).save(any());
        verify(desafioTentativaRespostaRepository).save(any());
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

        verify(usuarioRepository).save(new Usuario(request.getApelido()));
        verify(desafioTentativaRespostaRepository).save(desafioTentativaResposta);
    }

    private void executaValidacaoErrada(final DesafioTentativaRespostaRequest request) {
        DesafioTentativaResposta desafioTentativaResposta = desafioService.verificarResposta(request);
        then(desafioTentativaResposta.isCorreta()).isFalse();

        verify(usuarioRepository).save(new Usuario(request.getApelido()));
        verify(desafioTentativaRespostaRepository).save(desafioTentativaResposta);
    }
}
