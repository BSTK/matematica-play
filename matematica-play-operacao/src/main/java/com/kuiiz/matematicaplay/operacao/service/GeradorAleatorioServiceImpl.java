package com.kuiiz.matematicaplay.operacao.service;

import java.util.Random;

import org.springframework.stereotype.Service;
import com.kuiiz.matematicaplay.operacao.domain.Operador;

@Service
public class GeradorAleatorioServiceImpl implements GeradorAleatorioService {
	
	private static final int FATOR_MINIMO = 1;
	private static final int FATOR_MAXIMO = 99;
	private static final Random RANDOM = new Random();
	private static final Operador[] OPERADORES = Operador.values();

	
	@Override
	public int gerarFator() {
		return RANDOM.nextInt((FATOR_MAXIMO - FATOR_MINIMO) + 1) + FATOR_MINIMO;
	}

	@Override
	public Operador gerarOperador() {
		return OPERADORES[RANDOM.nextInt(OPERADORES.length)];
	}

}
