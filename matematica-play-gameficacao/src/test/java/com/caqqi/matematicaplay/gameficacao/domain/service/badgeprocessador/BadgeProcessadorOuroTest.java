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

public class BadgeProcessadorOuroTest {

    private BadgeProcessador processador = new BadgeProcessadorOuro();

    @Test
    @DisplayName("Deve retornar badge tipo prata quando total de pontos for maior que 400")
    public void deveRetornarBadgeTipoOuroQuandoTotalDePontosForMaiorQue400() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(401,
            Collections.emptyList(), new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo)
            .isNotEmpty()
            .isEqualTo(Optional.of(BadgeTipo.OURO));
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando quantidade de score cards for igual que 400")
    public void deveRetornarBadgeTipoVazioQuandoQuantidadeDeScorCardsForIgualQue150() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(400,
            Collections.emptyList(), new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando quantidade de score cards for menor que 400")
    public void deveRetornarBadgeTipoVazioQuandoQuantidadeDeScorCardsForMenorQue150() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(399,
            Collections.emptyList(), new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando quantidade de score for negativo")
    public void deveRetornarBadgeTipoVazioQuandoQuantidadeDeScorCardsForVazia() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(-1,
            Collections.emptyList(), new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando quantidade de score cards for nula")
    public void deveRetornarBadgeTipoVazioQuandoQuantidadeDeScorCardsForNula() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(null,
            null, new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }
}
