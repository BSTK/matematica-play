package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BadgeProcessadorNumeroDaSorte implements BadgeProcessador {

    private static final int NUMERO_DA_SORTE = 42;

    @Override
    public BadgeTipo tipo() {
        return BadgeTipo.NUMERO_DA_SORTE;
    }

    @Override
    public Optional<BadgeTipo> processarBadgeOptional(final Integer totalPontosAtual,
                                                      final List<ScoreCard> scoreCards,
                                                      final DesafioTentativaRespostaRequest request) {
        return request.getFatorA() == NUMERO_DA_SORTE
            || request.getFatorB() == NUMERO_DA_SORTE
            ? Optional.of(BadgeTipo.NUMERO_DA_SORTE)
            : Optional.empty();
    }
}
