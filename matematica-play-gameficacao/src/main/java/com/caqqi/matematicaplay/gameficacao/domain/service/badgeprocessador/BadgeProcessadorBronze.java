package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class BadgeProcessadorBronze implements BadgeProcessador {

    private static final int TOTAL_PONTOS_BRONZE = 50;

    @Override
    public BadgeTipo tipo() {
        return BadgeTipo.BRONZE;
    }

    @Override
    public Optional<BadgeTipo> processarBadgeOptional(final Integer totalPontosAtual,
                                                      final List<ScoreCard> scoreCards,
                                                      final DesafioTentativaRespostaRequest request) {
        return Objects.nonNull(totalPontosAtual) && totalPontosAtual > TOTAL_PONTOS_BRONZE
            ? Optional.of(BadgeTipo.BRONZE)
            : Optional.empty();
    }
}
