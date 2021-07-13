package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.emptyList;

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
    @DisplayName("Deve retornar badge tipo vazio quando total de pontos for igual que 400")
    public void deveRetornarBadgeTipoVazioQuandoTotalDePontosForIgualQue150() {
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
    @DisplayName("Deve retornar badge tipo vazio quando total de pontos for negativo")
    public void deveRetornarBadgeTipoVazioQuandoTotalDePontosForVazia() {
        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(-1,
            Collections.emptyList(), new DesafioTentativaRespostaRequest());

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve lançar excesão quando total de pontos for nula")
    public void deveLancarExcesaoQuandoTotalDePontosForNula() {
        Assertions
            .assertThatIllegalArgumentException()
            .isThrownBy(() -> processador.processarBadgeOptional(null, emptyList(), null))
            .withMessage("totalPontosAtual não pode ser nulo");
    }
}
