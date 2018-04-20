package com.kuiiz.matematicaplay.operacao.domain;

import java.util.stream.Stream;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Operador {

	SOMA("+"),
	SUBTRACAO("-"),
	MULTIPLICACAO("x"),
	DIVISAO("/");
	
	@JsonValue
	private final String simbolo;
	
	/**
	 * Operador
	 * @param descricao
	 * @param simbolo
	 */
	private Operador(String simbolo) {
		this.simbolo = simbolo;
	}
	
	
	/**
	 * To Enum
	 * 		Transforma um simbolo (+, -, *, /) em um enum de operador
	 */
	public static Operador toEnum(String simbolo) {
				
		Assert.isTrue("+-x/".contains(simbolo), "O simbolo " + simbolo + " é um operador inválido");
		
		return Stream.of(values())
					.filter(o -> o.getSimbolo().equals(simbolo))
					.findFirst()
					.get();
	}


	/**
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
	}
	
}
