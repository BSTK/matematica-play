package com.kuiiz.matematicaplay.operacao.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.enums.Operador;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OperacaoServiceTest {
	
	
	@MockBean
	public GeradorRandomicoService geradorService;
	
	@Autowired
	private OperacaoService operacaoService;
	
	@Test
	public void testCriaUmaOperacaoAleatoria() {
		
		/**
		 * Dado a chamada de geradorService.geraFator(), deve me retornar 100 e 50
		 * Dado a chamada de gerarOperador(), deve me retornar a operação soma (Operador.SOMA)
		 */
		given(geradorService.geraFator()).willReturn(100, 50);
		given(geradorService.geraOperador()).willReturn(Operador.SOMA);
		
		/**
		 * Quando eu chamar o método operacaoService.criaUmaOperacaoRadomica()
		 */
		Operacao operacao = operacaoService.criaUmaOperacaoRadomica();
		
		/**
		 * Então devo ter os seguintes resultados
		 */
		assertThat(operacao.getFatorA()).isEqualTo(100);
		assertThat(operacao.getFatorB()).isEqualTo(50);
		assertThat(operacao.getOperador()).isEqualTo(Operador.SOMA);
		assertThat(operacao.getResultado()).isEqualTo(150);
		
	}
	

}
