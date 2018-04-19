package com.kuiiz.matematicaplay.operacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuiiz.matematicaplay.operacao.domain.TentativaSolucao;
import com.kuiiz.matematicaplay.operacao.service.OperacaoService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/resultados")
public class TentativaSolucaoController {
	
	private final OperacaoService operacaoService;
	
	@Autowired
	public TentativaSolucaoController(OperacaoService operacaoService) {
		this.operacaoService = operacaoService;
	}
	
	
	@PostMapping
	public ResponseEntity<ResultadoResponse> postResultado(@RequestBody TentativaSolucao solucao) {
		boolean verificaTentativa = operacaoService.verificaTentativa(solucao);
		ResultadoResponse resultadoResponse = new ResultadoResponse(verificaTentativa);
		return ResponseEntity.ok(resultadoResponse);
	}
	
	
	@Getter
	@RequiredArgsConstructor
	@NoArgsConstructor(force = true)
	protected static final class ResultadoResponse {
		private final boolean correto;
	}

}
