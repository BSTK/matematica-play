package com.caqqi.matematicaplay.operacao.desafio;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class DesafioTentativaResposta {

    private Long id;
    private Long usuarioId;
    private int fatorA;
    private int fatorB;
    private int resultado;
    private boolean correta;
    private String operacao;

}
