package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.domain.entity.DesafioTentativaResposta;

public interface DesafioService {

    DesafioTentativaResposta verificarResposta(final DesafioTentativaRespostaRequest tentativaRequest);

}
