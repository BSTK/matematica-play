package com.kuiiz.matematicaplay.operacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.Operador;
import com.kuiiz.matematicaplay.operacao.domain.TentativaSolucao;

@Service
public class OperacaoServiceImpl implements OperacaoService {
	
	private GeradorAleatorioService geradorService;

	@Autowired
	public OperacaoServiceImpl(GeradorAleatorioService geradorService) {
		this.geradorService = geradorService;
	}

	
	@Override
	public Operacao criaUmaOperacaoAleatoria() {
		
		int fatorA = geradorService.gerarFator();
		int fatorB = geradorService.gerarFator();
		Operador operador = geradorService.gerarOperador();
		
		return new Operacao(fatorA, fatorB, operador);
	}

	
	@Override
	public boolean verificaTentativa(TentativaSolucao solucao) {
		return solucao.getResultado() == calculaOperacao(solucao.getOperacao());
	}


	/**
	 * Calcula Operacao
	 * @param operacao
	 * @return
	 */
	private int calculaOperacao(Operacao operacao) {
		
		int calculo = 0;
		
		switch (operacao.getOperador()) {
			case SOMA:
				calculo = operacao.getFatorA() + operacao.getFatorB();
				break;
			case SUBTRACAO:
				calculo = operacao.getFatorA() - operacao.getFatorB();
				break;
			case MULTIPLICACAO:
				calculo = operacao.getFatorA() * operacao.getFatorB();
				break;
			case DIVISAO:
				calculo = operacao.getFatorA() / operacao.getFatorB();
				break;
		}
		
		return calculo;
	}

}
