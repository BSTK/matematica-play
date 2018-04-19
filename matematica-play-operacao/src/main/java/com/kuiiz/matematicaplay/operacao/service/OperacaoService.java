package com.kuiiz.matematicaplay.operacao.service;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.TentativaSolucao;


public interface OperacaoService {
	
	
	/**
	 * Cria uma oeração randomica com fatores de 11 a 99
	 * @return
	 */
	Operacao criaUmaOperacaoAleatoria();
	
	/**
	 * Retorna true caso a tentativa de solução esteja correta
	 * @param tentativaSolucao
	 * @return
	 */
	boolean verificaTentativa(final TentativaSolucao solucao);

}
