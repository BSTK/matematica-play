package com.caqqi.matematicaplay.desafios.desafio.domain.service;

import com.caqqi.matematicaplay.desafios.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.desafios.domain.entity.DesafioTentativaResposta;
import com.caqqi.matematicaplay.desafios.domain.repository.DesafioTentativaRespostaRepository;
import com.caqqi.matematicaplay.desafios.domain.service.DesafioService;
import com.caqqi.matematicaplay.desafios.domain.service.DesafioServiceImpl;
import com.caqqi.matematicaplay.desafios.domain.service.GameficacaoServiceCliente;
import com.caqqi.matematicaplay.desafios.usuario.domain.entity.Usuario;
import com.caqqi.matematicaplay.desafios.usuario.domain.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DesafioServiceTest {

    private DesafioService desafioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private GameficacaoServiceCliente gameficacaoServiceCliente;

    @Mock
    private DesafioTentativaRespostaRepository desafioTentativaRespostaRepository;

    @BeforeEach
    void setUp() {
        desafioService = new DesafioServiceImpl(
            usuarioRepository,
            gameficacaoServiceCliente,
            desafioTentativaRespostaRepository
        );
    }

    @Test
    @DisplayName("Deve retornar as ultimas tentativas por usuario")
    void deveRetornarAsUltimasTentativasPorUsuario() {
        given(desafioTentativaRespostaRepository.findTop10ByUsuarioApelidoOrderByIdDesc(anyString()))
            .willReturn(Collections.emptyList());

        List<DesafioTentativaResposta> tentativaRespostas = desafioService.ultimasTentivas("USUARIO_AA");

        Assertions.assertThat(tentativaRespostas)
            .isNotNull()
            .isEmpty();

        verify(desafioTentativaRespostaRepository).findTop10ByUsuarioApelidoOrderByIdDesc(anyString());
    }

    @Test
    @DisplayName("Verifica usuário já existente")
    void verificaUsuarioJaExistente() {
        given(desafioTentativaRespostaRepository.save(any())).will(returnsFirstArg());

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
    void verificaTentativaDeRespostaCorretaOperacaoSoma() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 20, "+", "UsuarioAAB");

        executaValidacaoCorreta(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta errada operacao soma")
    void verificaTentativaDeRespostaErradaOperacaoSoma() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 25, "+", "UsuarioAAB");

        executaValidacaoErrada(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta correta operacao subtração")
    void verificaTentativaDeRespostaCorretaOperacaoSubtracao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 0, "-", "UsuarioAAB");

        executaValidacaoCorreta(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta errada operacao subtração")
    void verificaTentativaDeRespostaErradaOperacaoSubtracao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 25, "-", "UsuarioAAB");

        executaValidacaoErrada(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta correta operacao divisao")
    void verificaTentativaDeRespostaCorretaOperacaoDivisao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 1, "/", "UsuarioAAB");

        executaValidacaoCorreta(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta errada operacao divisao")
    void verificaTentativaDeRespostaErradaOperacaoDivisao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 0, "/", "UsuarioAAB");

        executaValidacaoErrada(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta correta operacao multiplicacao")
    void verificaTentativaDeRespostaCorretaOperacaoMultiplicacao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 100, "*", "UsuarioAAB");

        executaValidacaoCorreta(tentativaRequest);
    }

    @Test
    @DisplayName("Verifica tentativa de resposta errada operacao multiplicacao")
    void verificaTentativaDeRespostaErradaOperacaoMultiplicacao() {
        DesafioTentativaRespostaRequest tentativaRequest = new DesafioTentativaRespostaRequest(
            10, 10, 99, "*", "UsuarioAAB");

        executaValidacaoErrada(tentativaRequest);
    }

    private void executaValidacaoCorreta(final DesafioTentativaRespostaRequest request) {
        given(desafioTentativaRespostaRepository.save(any())).will(returnsFirstArg());

        DesafioTentativaResposta desafioTentativaResposta = desafioService.verificarResposta(request);
        then(desafioTentativaResposta.isCorreta()).isTrue();

        verify(usuarioRepository).save(new Usuario(request.getApelido()));
        verify(desafioTentativaRespostaRepository).save(desafioTentativaResposta);
        verify(gameficacaoServiceCliente).enviarTentativa(desafioTentativaResposta);
    }

    private void executaValidacaoErrada(final DesafioTentativaRespostaRequest request) {
        given(desafioTentativaRespostaRepository.save(any())).will(returnsFirstArg());

        DesafioTentativaResposta desafioTentativaResposta = desafioService.verificarResposta(request);
        then(desafioTentativaResposta.isCorreta()).isFalse();

        verify(usuarioRepository).save(new Usuario(request.getApelido()));
        verify(desafioTentativaRespostaRepository).save(desafioTentativaResposta);
        verify(gameficacaoServiceCliente).enviarTentativa(desafioTentativaResposta);
    }
}
