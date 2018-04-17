package com.kuiiz.matematicaplay.operacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.Operador;

@Service
public class OperacaoServiceImpl implements OperacaoService {
	
	@Autowired
	private GeradorAleatorioService geradorService;

	@Override
	public Operacao criaUmaOperacaoAleatoria() {
		
		int fatorA = geradorService.gerarFator();
		int fatorB = geradorService.gerarFator();
		Operador operador = geradorService.gerarOperador();
		
		return new Operacao(fatorA, fatorB, operador);
	}

}
