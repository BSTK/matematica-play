package com.kuiiz.matematicaplay.operacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.kuiiz.matematicaplay.operacao.service.OperacaoService;

@RestController
public class OperacaoController {
	
	private final OperacaoService operacaoService;
	
	@Autowired
	public OperacaoController(OperacaoService operacaoService) {
		this.operacaoService = operacaoService;
	}

}
