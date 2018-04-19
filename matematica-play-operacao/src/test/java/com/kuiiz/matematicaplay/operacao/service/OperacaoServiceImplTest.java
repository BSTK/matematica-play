package com.kuiiz.matematicaplay.operacao.service;

import static com.kuiiz.matematicaplay.operacao.domain.Operador.DIVISAO;
import static com.kuiiz.matematicaplay.operacao.domain.Operador.MULTIPLICACAO;
import static com.kuiiz.matematicaplay.operacao.domain.Operador.SOMA;
import static com.kuiiz.matematicaplay.operacao.domain.Operador.SUBTRACAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.Operador;
import com.kuiiz.matematicaplay.operacao.domain.TentativaSolucao;
import com.kuiiz.matematicaplay.operacao.domain.Usuario;

public class OperacaoServiceImplTest {
	
	@Mock
	public GeradorAleatorioService geradorService;
	
	private OperacaoServiceImpl operacaoServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		operacaoServiceImpl = new OperacaoServiceImpl(geradorService);
	}
	
	
	@Test
	public void testCriaUmaOperacaoValidandoOResultado() {		
		
		executaOperacao(100, SOMA, 50, 150);
		executaOperacao(100, SUBTRACAO, 50, 50);
		executaOperacao(100, MULTIPLICACAO, 50, 5000);
		executaOperacao(100, DIVISAO, 50, 2);
		
	}
	
	@Test
	public void testVerificaTentativaCorreta() {
		assertThat(verificaTentativa(100, 100, 200, SOMA)).isTrue();
	}
	
	@Test
	public void testVerificaTentativaErrada() {
		assertThat(verificaTentativa(100, 100, 2000, SOMA)).isFalse();
		
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
		Operacao operacao = operacaoServiceImpl.criaUmaOperacaoAleatoria();
		
		/**
		 * Então devo ter os seguintes resultados
		 */
		assertThat(operacao.getFatorA()).isEqualTo(fatorA);
		assertThat(operacao.getFatorB()).isEqualTo(fatorB);
		assertThat(operacao.getOperador()).isEqualTo(operador);
	}
	
	/**
	 * VerificaTentativa
	 * @param fatorA
	 * @param fatorB
	 * @param resultado
	 * @param operador
	 * @return
	 */
	private boolean verificaTentativa(int fatorA, int fatorB, int resultado, Operador operador) {
		
		Operacao operacao = new Operacao(fatorA, fatorB, operador);
		Usuario usuario = new Usuario("Sebastião");
		TentativaSolucao solucao = new TentativaSolucao(usuario, operacao, resultado);
		
		return operacaoServiceImpl.verificaTentativa(solucao);
	}


}
