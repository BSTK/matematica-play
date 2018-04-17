package com.kuiiz.matematicaplay.operacao.service;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;

public interface OperacaoService {
	
	
	/**
	 * Cria uma oeração randomica com fatores de 11 a 99
	 * @return
	 */
	Operacao criaUmaOperacaoAleatoria();

}
