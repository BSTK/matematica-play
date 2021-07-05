package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BadgeProcessadorOuro implements BadgeProcessador {

    private static final int QUANTIDADE_SCORE_CARDS_OURO = 400;

    @Override
    public BadgeTipo tipo() {
        return BadgeTipo.OURO;
    }

    @Override
    public Optional<BadgeTipo> processarBadgeOptional(final Integer totalPontosAtual,
                                                      final List<ScoreCard> scoreCards,
                                                      final DesafioTentativaRespostaRequest request) {
        return totalPontosAtual > QUANTIDADE_SCORE_CARDS_OURO
            ? Optional.of(BadgeTipo.OURO)
            : Optional.empty();
    }
}
