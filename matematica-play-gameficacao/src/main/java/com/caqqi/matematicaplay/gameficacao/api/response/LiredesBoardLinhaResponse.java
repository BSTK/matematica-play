package com.caqqi.matematicaplay.gameficacao.api.response;

import lombok.Data;

import java.util.List;

@Data
public class LiredesBoardLinhaResponse {

    private Long usuarioId;
    private Long totalPontos;
    private List<String> badges;

}
