package com.caqqi.matematicaplay.desafios.domain;

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
