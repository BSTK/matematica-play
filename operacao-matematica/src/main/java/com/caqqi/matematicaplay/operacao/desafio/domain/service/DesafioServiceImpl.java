package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.domain.DesafioOperacao;
import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.DesafioTentativaResposta;
import com.caqqi.matematicaplay.operacao.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class DesafioServiceImpl implements DesafioService {

    @Override
    public DesafioTentativaResposta verificarResposta(final DesafioTentativaRespostaRequest tentativaRequest) {
        final int resultadoTentaiva = executarOperacao(tentativaRequest);
        final boolean respostaCorreta = tentativaRequest.getResposta() == resultadoTentaiva;
        final Usuario usuario = new Usuario(null, tentativaRequest.getApelido());

        return new DesafioTentativaResposta(
            null,
            usuario,
            tentativaRequest.getFatorA(),
            tentativaRequest.getFatorB(),
            resultadoTentaiva,
            respostaCorreta,
            tentativaRequest.getOperacao());
    }

    private int executarOperacao(final DesafioTentativaRespostaRequest tentativaRequest) {
        if (DesafioOperacao.ADICAO.getOperador().equals(tentativaRequest.getOperacao())) {
            return tentativaRequest.getFatorA() + tentativaRequest.getFatorB();
        }

        if (DesafioOperacao.DIVISAO.getOperador().equals(tentativaRequest.getOperacao())) {
            return tentativaRequest.getFatorA() / tentativaRequest.getFatorB();
        }

        if (DesafioOperacao.SUBTRACAO.getOperador().equals(tentativaRequest.getOperacao())) {
            return tentativaRequest.getFatorA() - tentativaRequest.getFatorB();
        }

        if (DesafioOperacao.MULTIPLICACAO.getOperador().equals(tentativaRequest.getOperacao())) {
            return tentativaRequest.getFatorA() * tentativaRequest.getFatorB();
        }

        throw new IllegalArgumentException("Operação inválida!");
    }

}
