package com.kuiiz.matematicaplay.operacao.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Operacao {

	private final int fatorA;
	private final int fatorB;
	private final Operador operador;
	
	/**
	 * Operacao
	 * Empty constructor for JSON (de) serialization
	 */
	public Operacao() {
		this(0, 0, Operador.SOMA);
	}
	
}
