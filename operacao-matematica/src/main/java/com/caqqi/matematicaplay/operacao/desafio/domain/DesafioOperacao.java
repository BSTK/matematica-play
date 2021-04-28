package com.caqqi.matematicaplay.operacao.desafio.domain;

import java.util.stream.Stream;

public enum DesafioOperacao {

    ADICAO("+", "Adição"),
    DIVISAO("/", "Divisão"),
    SUBTRACAO("-", "Subtração"),
    MULTIPLICACAO("*", "Multiplicação");

    private final String operador;
    private final String descricao;

    DesafioOperacao(final String operador,
                    final String descricao) {
        this.operador = operador;
        this.descricao = descricao;
    }

    public DesafioOperacao of(final String operador) {
        return Stream
            .of(values())
            .filter(operacao -> operador.equals(operacao.getOperador()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format("Operador inválido ( %s ).", operador)));
    }

    public String getOperador() {
        return operador;
    }

    public String getDescricao() {
        return descricao;
    }
}
