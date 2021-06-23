package com.caqqi.matematicaplay.operacao.desafio.api.response;

import lombok.Value;

@Value
public class DesafioResponse {

    int fatorA;
    int fatorB;
    int[] alternativas;
    String operacao;

}
