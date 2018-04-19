package com.kuiiz.matematicaplay.operacao.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class TentativaSolucao {
	
	private final Usuario usuario;
	private final Operacao operacao;
	private final int resultado;
	
	/**
	 * TentativaDeSolucao
	 *  Empty constructor for JSON (de) serialization
	 */
	public TentativaSolucao() {
		this(new Usuario(), new Operacao(), -1);
	}

}
