package com.kuiiz.matematicaplay.operacao.service;

import static com.kuiiz.matematicaplay.operacao.domain.Operador.DIVISAO;
import static com.kuiiz.matematicaplay.operacao.domain.Operador.MULTIPLICACAO;
import static com.kuiiz.matematicaplay.operacao.domain.Operador.SOMA;
import static com.kuiiz.matematicaplay.operacao.domain.Operador.SUBTRACAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.Operador;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OperacaoServiceTest {
	
	
	@MockBean
	public GeradorAleatorioService geradorService;
	
	@Autowired
	private OperacaoService operacaoService;
	
	@Test
	public void testCriaUmaOperacaoValidandoOResultado() {		
		
		executaOperacao(100, SOMA, 50, 150);
		executaOperacao(100, SUBTRACAO, 50, 50);
		executaOperacao(100, MULTIPLICACAO, 50, 5000);
		executaOperacao(100, DIVISAO, 50, 2);
		
	}
	
	
	/**
	 * Executa Operacao
	 * @param operador
	 * @param fatorA
	 * @param fatorB
	 * @param resultado
	 */
	private void executaOperacao(int fatorA, Operador operador, int fatorB, int resultado) {
	
		/**
		 * Dado a chamada de geradorService.geraFator(), deve me retornar 100 e 50
		 */
		given(geradorService.gerarFator()).willReturn(fatorA, fatorB);
		
		/**
		 * Dado a chamada de gerarOperador(), deve me retornar a operação soma (Operador.DIVISAO)
		 */
		given(geradorService.gerarOperador()).willReturn(operador);
		
		/**
		 * Quando eu chamar o método operacaoService.criaUmaOperacaoRadomica()
		 */
		Operacao operacao = operacaoService.criaUmaOperacaoAleatoria();
		
		/**
		 * Então devo ter os seguintes resultados
		 */
		assertThat(operacao.getFatorA()).isEqualTo(fatorA);
		assertThat(operacao.getFatorB()).isEqualTo(fatorB);
		assertThat(operacao.getOperador()).isEqualTo(operador);
		assertThat(operacao.getResultado()).isEqualTo(resultado);
	}
	
}
