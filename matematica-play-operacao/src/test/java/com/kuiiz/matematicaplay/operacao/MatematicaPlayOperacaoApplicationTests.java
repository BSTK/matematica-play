package com.kuiiz.matematicaplay.operacao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.enums.Operador;

// @SpringBootTest
// @RunWith(SpringRunner.class)
public class MatematicaPlayOperacaoApplicationTests {

	@Test
	public void testOperacaoSoma() {
		Operacao operacaoDeSoma = new Operacao(10, 20, Operador.toEnum("+"));
		assertThat(operacaoDeSoma.getResultado()).isEqualTo(30);
	}
	
	@Test
	public void testOperacaoSubtracao() {
		Operacao operacaoDeSubtracao = new Operacao(10, 20, Operador.toEnum("-"));
		assertThat(operacaoDeSubtracao.getResultado()).isEqualTo(-10);
	}
	
	@Test
	public void testOperacaoMultiplicacao() {
		Operacao operacaoDeMultiplicacao = new Operacao(10, 20, Operador.toEnum("*"));
		assertThat(operacaoDeMultiplicacao.getResultado()).isEqualTo(200);
	}
	
	@Test
	public void testOperacaoDivisao() {
		Operacao operacaoDeDivisao = new Operacao(100, 2, Operador.toEnum("/"));
		assertThat(operacaoDeDivisao.getResultado()).isEqualTo(50);
	}

}
