package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.DesafioTentativaResposta;

public interface DesafioService {

    DesafioTentativaResposta verificarResposta(DesafioTentativaRespostaRequest tentativaRequest);

}
