package com.kuiiz.matematicaplay.operacao.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Usuario {

	private final String apelido;
	
	/**
	 * Usuario
	 * Empty constructor for JSON (de) serialization
	 */
	public Usuario() {
		this("");
	}
	
}
