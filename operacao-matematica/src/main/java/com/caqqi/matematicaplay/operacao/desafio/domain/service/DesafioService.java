package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.domain.entity.DesafioTentativaResposta;

import java.util.List;

public interface DesafioService {

    List<DesafioTentativaResposta> ultimasTentivas(final String usuarioApelido);

    DesafioTentativaResposta verificarResposta(final DesafioTentativaRespostaRequest tentativaRequest);

}
