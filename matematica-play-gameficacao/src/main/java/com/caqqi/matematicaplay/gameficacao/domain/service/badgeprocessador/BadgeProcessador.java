package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;

import java.util.List;
import java.util.Optional;

public interface BadgeProcessador {

    BadgeTipo tipo();

    Optional<BadgeTipo> processarBadgeOptional(final Integer totalPontosAtual,
                                               final List<ScoreCard> scoreCards,
                                               final DesafioTentativaRespostaRequest request);
}
