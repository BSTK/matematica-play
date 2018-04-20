package com.kuiiz.matematicaplay.operacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.service.OperacaoService;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController {
	
	private final OperacaoService operacaoService;
	
	@Autowired
	public OperacaoController(OperacaoService operacaoService) {
		this.operacaoService = operacaoService;
	}
	
	@GetMapping("/aleatoria")
	public Operacao operacao() {
		return operacaoService.criaUmaOperacaoAleatoria();
	}

}
