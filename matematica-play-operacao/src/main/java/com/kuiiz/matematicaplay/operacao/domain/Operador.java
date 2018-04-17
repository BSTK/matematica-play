package com.kuiiz.matematicaplay.operacao.domain;

import java.util.stream.Stream;

import org.springframework.util.Assert;

public enum Operador {

	SOMA("soma", "+"),
	SUBTRACAO("subtracao", "-"),
	MULTIPLICACAO("multiplicacao", "*"),
	DIVISAO("divisao", "/");
	
	private final String descricao;
	private final String simbolo;
	
	/**
	 * Operador
	 * @param descricao
	 * @param simbolo
	 */
	private Operador(String descricao, String simbolo) {
		this.descricao = descricao;
		this.simbolo = simbolo;
	}
	
	
	/**
	 * To Enum
	 * 		Transforma um simbolo (+, -, *, /) em um enum de operador
	 */
	public static Operador toEnum(String simbolo) {
		
		Assert.isTrue("+-*/".contains(simbolo), "O simbolo " + simbolo + " é um operador inválido");
		
		return Stream.of(values())
					.filter(o -> o.getSimbolo().equals(simbolo))
					.findFirst()
					.get();
	}

	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
	}
	
}
