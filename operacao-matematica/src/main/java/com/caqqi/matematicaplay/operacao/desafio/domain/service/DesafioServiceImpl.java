package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.domain.DesafioOperacao;
import com.caqqi.matematicaplay.operacao.desafio.domain.entity.DesafioTentativaResposta;
import com.caqqi.matematicaplay.operacao.usuario.domain.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class DesafioServiceImpl implements DesafioService {

    @Override
    public DesafioTentativaResposta verificarResposta(final DesafioTentativaRespostaRequest tentativaRequest) {
        final int resultadoTentativa = DesafioOperacao
                                            .of(tentativaRequest.getOperacao())
                                            .execute(tentativaRequest.getFatorA(), tentativaRequest.getFatorB());

        final boolean respostaCorreta = tentativaRequest.getResposta() == resultadoTentativa;
        final Usuario usuario = new Usuario(null, tentativaRequest.getApelido());

        return new DesafioTentativaResposta(
            null,
            usuario,
            tentativaRequest.getFatorA(),
            tentativaRequest.getFatorB(),
            resultadoTentativa,
            respostaCorreta,
            tentativaRequest.getOperacao());
    }

}
