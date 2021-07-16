package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

class BadgeProcessadorPrataTest {

    private BadgeProcessador processador = new BadgeProcessadorPrata();

    @Test
    @DisplayName("Deve retornar badge tipo prata quando total de pontos for maior que 150")
    void deveRetornarBadgeTipoPrataQuandoQuantidadeDeScorCardsForMaiorQue150() {
        final List<ScoreCard> scoreCards = new ArrayList<>();
        IntStream
            .range(1, 152)
            .forEach(value -> scoreCards.add(new ScoreCard()));

        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(1,
            scoreCards, new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo)
            .isNotEmpty()
            .isEqualTo(Optional.of(BadgeTipo.PRATA));
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando quantidade de score cards for igual que 150")
    void deveRetornarBadgeTipoVazioQuandoQuantidadeDeScorCardsForIgualQue150() {
        final List<ScoreCard> scoreCards = new ArrayList<>();
        IntStream
            .range(1, 151)
            .forEach(value -> scoreCards.add(new ScoreCard()));

        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(1,
            scoreCards, new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando quantidade de score cards for menor que 150")
    void deveRetornarBadgeTipoVazioQuandoQuantidadeDeScorCardsForMenorQue150() {
        final List<ScoreCard> scoreCards = new ArrayList<>();
        IntStream
            .range(1, 150)
            .forEach(value -> scoreCards.add(new ScoreCard()));

        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(1,
            scoreCards, new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando quantidade de score cards for vazia")
    void deveRetornarBadgeTipoVazioQuandoQuantidadeDeScorCardsForVazia() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(1,
            Collections.emptyList(), new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve lançar excesão quando score cards for nulo")
    void deveLancarExcesaoQuandoScoreCardsForNulo() {
        Assertions
            .assertThatIllegalArgumentException()
            .isThrownBy(() -> processador.processarBadgeOptional(null, null, null))
            .withMessage("scoreCards não pode ser nulo");
    }
}
