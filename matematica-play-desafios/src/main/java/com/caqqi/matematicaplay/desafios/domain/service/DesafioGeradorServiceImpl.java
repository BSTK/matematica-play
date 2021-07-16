package com.caqqi.matematicaplay.desafios.domain.service;

import com.caqqi.matematicaplay.desafios.domain.Desafio;
import com.caqqi.matematicaplay.desafios.domain.DesafioOperacao;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class DesafioGeradorServiceImpl implements DesafioGeradorService {

    private static final int FATOR_MINIMO = 11;
    private static final int FATOR_MAXIMO = 100;

    private final SecureRandom random;

    public DesafioGeradorServiceImpl() {
        this.random = new SecureRandom();
    }

    public DesafioGeradorServiceImpl(final SecureRandom random) {
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
        final int[] alternativas = new int[] { fator(), fator(), fator(), alternativaCorreta };

        return embaralhar(alternativas);
    }

    private int[] embaralhar(final int[] alternativas) {
        for (int i = 0; i < alternativas.length; i++) {
            int indiceSwap = random.nextInt(alternativas.length);
            int indexTemporario = alternativas[indiceSwap];
            alternativas[indiceSwap] = alternativas[i];
            alternativas[i] = indexTemporario;
        }

        return alternativas;
    }
}
