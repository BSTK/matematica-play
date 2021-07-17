package com.caqqi.matematicaplay.gameficacao.api.response;

import com.caqqi.matematicaplay.gameficacao.core.config.Generated;
import lombok.Data;

import java.util.List;

@Data
@Generated
public class LiredesBoardLinhaResponse {

    private Long usuarioId;
    private Long totalPontos;
    private List<String> badges;

}
