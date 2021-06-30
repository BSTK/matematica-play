package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.domain.DesafioOperacao;
import com.caqqi.matematicaplay.operacao.desafio.domain.entity.Desafio;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DesafioGeradorServiceImpl implements DesafioGeradorService {

    private static final int FATOR_MINIMO = 11;
    private static final int FATOR_MAXIMO = 100;

    private final Random random;

    public DesafioGeradorServiceImpl() {
        this.random = new Random();
    }

    public DesafioGeradorServiceImpl(final Random random) {
        this.random = random;
    }

    @Override
    public Desafio gerarDesafioAleatorio() {
        final int fatorA = fator();
        final int fatorB = fator();
        final String operacao = operacao();
        final int[] alternativas = alternativas(fatorA, fatorB, operacao);

        return new Desafio(fatorA, fatorB, alternativas, operacao);
    }

    private int fator() {
        return random.nextInt(FATOR_MAXIMO - FATOR_MINIMO) + FATOR_MINIMO;
    }

    private String operacao() {
        return DesafioOperacao
            .values()[random.nextInt(DesafioOperacao.values().length)]
            .getOperador();
    }

    private int[] alternativas(final int fatorA, final int fatorB, final String operacao) {
        final int alternativaCorreta = DesafioOperacao.of(operacao).execute(fatorA, fatorB);

        return new int[] {
            fator(),
            fator(),
            fator(),
            alternativaCorreta
        };
    }
}
