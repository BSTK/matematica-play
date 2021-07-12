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

public class BadgeProcessadorBronzeTest {

    private BadgeProcessador processador = new BadgeProcessadorBronze();

    @Test
    @DisplayName("Deve retornar badge tipo bronze quando total de pontos for maior que 50")
    public void deveRetornarBadgeTipoBrnonzeQuandoTotalPontosForMaiorQue50() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(51,
            List.of(new ScoreCard()),
            new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo)
            .isNotEmpty()
            .isEqualTo(Optional.of(BadgeTipo.BRONZE));
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando total de pontos for igual que 50")
    public void deveRetornarBadgeTipoVazioQuandoTotalPontosForIgualQue50() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(50,
            Collections.emptyList(),
            new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando total de pontos for menor que 50")
    public void deveRetornarBadgeTipoVazioQuandoTotalPontosForMenorQue50() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(49,
            Collections.emptyList(),
            new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }
}
