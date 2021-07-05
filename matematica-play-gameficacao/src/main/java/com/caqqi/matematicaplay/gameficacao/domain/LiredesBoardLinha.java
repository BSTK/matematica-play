package com.caqqi.matematicaplay.gameficacao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.util.List;

@Data
@AllArgsConstructor
public class LiredesBoardLinha {

    private final Long usuarioId;
    private final Long totalPontos;

    @With
    private final List<String> badges;

    public LiredesBoardLinha(final Long usuarioId,
                             final Long totalPontos) {
        this.usuarioId = usuarioId;
        this.totalPontos = totalPontos;
        this.badges = List.of();
    }
}
