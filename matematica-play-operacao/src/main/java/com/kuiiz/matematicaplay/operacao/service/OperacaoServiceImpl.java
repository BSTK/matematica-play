package com.kuiiz.matematicaplay.operacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.enums.Operador;

@Service
public class OperacaoServiceImpl implements OperacaoService {
	
	@Autowired
	private GeradorRandomicoService geradorService;

	@Override
	public Operacao criaUmaOperacaoRadomica() {
		
		int fatorA = geradorService.geraFator();
		int fatorB = geradorService.geraFator();
		Operador operador = geradorService.geraOperador();
		
		return new Operacao(fatorA, fatorB, operador);
	}

}
