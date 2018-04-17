package com.kuiiz.matematicaplay.operacao.service;

import com.kuiiz.matematicaplay.operacao.domain.Operador;

public interface GeradorAleatorioService {

	/**
	 * Gera um fator entre 11 e 99 para o operação
	 * @return
	 */
	int gerarFator();
	
	
	/**
	 * Gera uma operação aleatória
	 * @return
	 */
	Operador gerarOperador();
	
}
