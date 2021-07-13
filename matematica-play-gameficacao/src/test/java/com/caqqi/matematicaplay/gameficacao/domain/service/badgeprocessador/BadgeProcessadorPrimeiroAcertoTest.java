package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BadgeProcessadorPrimeiroAcertoTest {

    private BadgeProcessador processador = new BadgeProcessadorPrimeiroAcerto();

    @Test
    @DisplayName("Deve retornar badge tipo primeiro acerto quando scorecards for 1")
    public void deveRetornarBadgeTipoPrimeiroAcertoQuandoScorecardsFor1() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(10,
            List.of(new ScoreCard()),
            new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo)
            .isNotEmpty()
            .isEqualTo(Optional.of(BadgeTipo.PRIMEIRO_ACERTO));
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando scorecards for diferente de 1")
    public void deveRetornarBadgeTipoVazioQuandoScorecardsForDiferenteDe1() {
        Optional<BadgeTipo> badgeTipoA = processador.processarBadgeOptional(10,
            List.of(new ScoreCard(), new ScoreCard(), new ScoreCard()),
            new DesafioTentativaRespostaRequest());

        Optional<BadgeTipo> badgeTipoB = processador.processarBadgeOptional(10,
            Collections.emptyList(),
            new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipoA).isEmpty();
        Assertions.assertThat(badgeTipoB).isEmpty();
    }

    @Test
    @DisplayName("Deve lançar excesão quando score cards for nulo")
    public void deveLancarExcesaoQuandoScoreCardsForNulo() {
        Assertions
            .assertThatIllegalArgumentException()
            .isThrownBy(() -> processador.processarBadgeOptional(null, null, null))
            .withMessage("scoreCards não pode ser nulo");
    }
}
