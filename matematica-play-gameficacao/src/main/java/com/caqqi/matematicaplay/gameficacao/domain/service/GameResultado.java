package com.caqqi.matematicaplay.gameficacao.domain.service;

import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GameResultado {
    private final int pontos;
    private final List<BadgeTipo> badges;
}
