package com.caqqi.matematicaplay.operacao.desafio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Desafio {

    private int fatorA;
    private int fatorB;
    private int[] alternativas;
    private String operacao;

}
