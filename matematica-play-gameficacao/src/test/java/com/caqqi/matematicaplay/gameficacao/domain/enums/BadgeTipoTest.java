package com.caqqi.matematicaplay.gameficacao.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTipoTest {

    @Test
    @DisplayName("Deve retornar as descricoes validas")
    void deveRetornarAsDescricaoValidas() {
        Assertions.assertThat(BadgeTipo.BRONZE.getDescricao()).isNotEmpty().isEqualTo("Bronze");
        Assertions.assertThat(BadgeTipo.PRATA.getDescricao()).isNotEmpty().isEqualTo("Prata");
        Assertions.assertThat(BadgeTipo.OURO.getDescricao()).isNotEmpty().isEqualTo("Ouro");
        Assertions.assertThat(BadgeTipo.NUMERO_DA_SORTE.getDescricao())
            .isNotEmpty().isEqualTo("Número da sorte");
        Assertions.assertThat(BadgeTipo.PRIMEIRO_ACERTO.getDescricao())
            .isNotEmpty().isEqualTo("Primeira tentica correta");
    }

}
