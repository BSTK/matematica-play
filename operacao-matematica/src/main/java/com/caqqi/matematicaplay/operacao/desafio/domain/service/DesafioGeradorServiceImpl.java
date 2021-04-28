package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.Desafio;
import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.DesafioOperacao;
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
    public Desafio gerarDesafioRandomico() {
        return new Desafio(
            fator(),
            fator(),
            operacao()
        );
    }

    private int fator() {
        return random.nextInt(FATOR_MAXIMO - FATOR_MINIMO) + FATOR_MINIMO;
    }

    private String operacao() {
        return DesafioOperacao
            .values()[random.nextInt(DesafioOperacao.values().length)]
            .getOperador();
    }
}
