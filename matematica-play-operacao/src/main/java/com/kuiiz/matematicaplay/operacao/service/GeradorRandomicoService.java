package com.kuiiz.matematicaplay.operacao.service;

import com.kuiiz.matematicaplay.operacao.domain.enums.Operador;

public interface GeradorRandomicoService {

	/**
	 * Gera um fator entre 11 e 99 para o operação
	 * @return
	 */
	int geraFator();
	
	
	/**
	 * Gera uma operação aleatória
	 * @return
	 */
	Operador geraOperador();
	
}
