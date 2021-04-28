package com.caqqi.matematicaplay.operacao.desafio.domain.service;

import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.Desafio;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DesafioGeradorServiceImpl implements DesafioGeradorService {

    private final Random random;

    public DesafioGeradorServiceImpl() {
        this.random = new Random();
    }

    public DesafioGeradorServiceImpl(final Random random) {
        this.random = random;
    }

    @Override
    public Desafio gerarDesafioRandomico() {
        return null;
    }
}
