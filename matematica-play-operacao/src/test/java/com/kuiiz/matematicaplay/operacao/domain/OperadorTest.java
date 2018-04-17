package com.kuiiz.matematicaplay.operacao.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import com.kuiiz.matematicaplay.operacao.domain.Operador;

public class OperadorTest {

	@Test
	public void testCriaUmOperadorDeAcorComSeuSimbolo() {
		
		assertThat(Operador.toEnum("+")).isEqualTo(Operador.SOMA);
		assertThat(Operador.toEnum("-")).isEqualTo(Operador.SUBTRACAO);
		assertThat(Operador.toEnum("*")).isEqualTo(Operador.MULTIPLICACAO);
		assertThat(Operador.toEnum("/")).isEqualTo(Operador.DIVISAO);
		
	}
	
	@Test
	public void testDeveRetornarNullPorSimboloInvalido() {
			
		assertThatError("&");
		assertThatError("#");
		assertThatError("@");
		assertThatError("(");
		
	}
	
	private void assertThatError(String simbolo) {
		assertThatThrownBy(() -> { Operador.toEnum(simbolo); })
				.isInstanceOf(IllegalArgumentException.class).hasMessageContaining("operador inválido");
	}
	
}
