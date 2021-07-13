package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Component
public class BadgeProcessadorPrata implements BadgeProcessador {

    private static final int QUANTIDADE_SCORE_CARDS_PRATA = 150;

    @Override
    public BadgeTipo tipo() {
        return BadgeTipo.PRATA;
    }

    @Override
    public Optional<BadgeTipo> processarBadgeOptional(final Integer totalPontosAtual,
                                                      final List<ScoreCard> scoreCards,
                                                      final DesafioTentativaRespostaRequest request) {
        Assert.notNull(scoreCards, "scoreCards não pode ser nulo");

        return scoreCards.size() > QUANTIDADE_SCORE_CARDS_PRATA
            ? Optional.of(BadgeTipo.PRATA)
            : Optional.empty();
    }
}
