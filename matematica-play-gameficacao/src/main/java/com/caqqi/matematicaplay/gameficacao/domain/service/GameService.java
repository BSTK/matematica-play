package com.caqqi.matematicaplay.gameficacao.domain.service;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;

public interface GameService {

    GameResultado novaTentativaDoUsuario(DesafioTentativaRespostaRequest request);
}
