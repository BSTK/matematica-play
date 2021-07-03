package com.caqqi.matematicaplay.desafios.domain.service;

import com.caqqi.matematicaplay.desafios.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.desafios.domain.entity.DesafioTentativaResposta;

import java.util.List;

public interface DesafioService {

    List<DesafioTentativaResposta> ultimasTentivas(final String usuarioApelido);

    DesafioTentativaResposta verificarResposta(final DesafioTentativaRespostaRequest tentativaRequest);

}
