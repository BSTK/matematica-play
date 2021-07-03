package com.caqqi.matematicaplay.desafios.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BadgeTipo {

    BRONZE("Bronze"),
    PRATA("Prata"),
    OURO("Ouro"),
    PRIMEIRO_ACERTO("Primeira tentica correta"),
    NUMERO_DA_SORTE("Número da sorte");

    private final String descricao;
}
