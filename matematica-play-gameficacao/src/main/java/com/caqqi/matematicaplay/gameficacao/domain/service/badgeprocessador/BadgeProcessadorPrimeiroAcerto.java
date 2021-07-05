package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BadgeProcessadorPrimeiroAcerto implements BadgeProcessador {

    private static final int QUANTIDADE_SCORE_CARDS_PRIMEIRO_ACERTO = 1;

    @Override
    public BadgeTipo tipo() {
        return BadgeTipo.PRIMEIRO_ACERTO;
    }

    @Override
    public Optional<BadgeTipo> processarBadgeOptional(final Integer totalPontosAtual,
                                                      final List<ScoreCard> scoreCards,
                                                      final DesafioTentativaRespostaRequest request) {
        return scoreCards.size() == QUANTIDADE_SCORE_CARDS_PRIMEIRO_ACERTO
            ? Optional.of(BadgeTipo.PRIMEIRO_ACERTO)
            : Optional.empty();
    }
}
