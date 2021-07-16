package com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.Collections.emptyList;

class BadgeProcessadorNumeroDaSorteTest {

    private BadgeProcessador processador = new BadgeProcessadorNumeroDaSorte();

    @Test
    @DisplayName("Deve retornar badge tipo número da sorte quando fator A for 42")
    void deveRetornarBadgeTipoNumeroDaSorteQuandoFatorAFor42() {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest();
        request.setFatorA(42);
        request.setFatorB(40);

        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(1,
            emptyList(), request);

        Assertions.assertThat(badgeTipo)
            .isNotEmpty()
            .isEqualTo(Optional.of(BadgeTipo.NUMERO_DA_SORTE));
    }

    @Test
    @DisplayName("Deve retornar badge tipo número da sorte quando fator B for 42")
    void deveRetornarBadgeTipoNumeroDaSorteQuandoFatorBFor42() {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest();
        request.setFatorA(0);
        request.setFatorB(42);

        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(1,
            emptyList(), request);

        Assertions.assertThat(badgeTipo)
            .isNotEmpty()
            .isEqualTo(Optional.of(BadgeTipo.NUMERO_DA_SORTE));
    }

    @Test
    @DisplayName("Deve retornar badge tipo vazio quando fator A e fator B for diferente de 42")
    void deveRetornarBadgeTipoVazioQuandoFatorAEFatorBForDiferenteDe42() {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest();
        request.setFatorA(10);
        request.setFatorB(10);

        Optional<BadgeTipo> badgeTipo = processador.processarBadgeOptional(1,
            emptyList(), request);

        Assertions.assertThat(badgeTipo).isEmpty();
    }

    @Test
    @DisplayName("Deve lançar excesão quando a request for nula")
    void deveLancarExcesaoQuandoARequestForNula() {
        Assertions
            .assertThatIllegalArgumentException()
            .isThrownBy(() -> processador.processarBadgeOptional(1, emptyList(), null))
            .withMessage("DesafioTentativaRespostaRequest não pode ser nula");
    }

}
