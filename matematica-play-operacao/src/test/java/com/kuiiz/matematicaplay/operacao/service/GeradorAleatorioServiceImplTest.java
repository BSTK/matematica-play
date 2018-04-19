package com.kuiiz.matematicaplay.operacao.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.kuiiz.matematicaplay.operacao.domain.Operador;

public class GeradorAleatorioServiceImplTest {
	
	private GeradorAleatorioServiceImpl geradorAleatorioServiceImpl;
	
	@Before
	public void setUp() {
		geradorAleatorioServiceImpl = new GeradorAleatorioServiceImpl();
	}
	
	
	@Test
	public void testGeraFatoresEntreZeroEMil() {
		
		List<Integer> fatoresAleatorios = IntStream
											.range(0, 1000)
											.map(i -> geradorAleatorioServiceImpl.gerarFator())
											.boxed()
											.collect(Collectors.toList());
		
		assertThat(fatoresAleatorios)
			.containsOnlyElementsOf(
					IntStream
					.range(11, 100)
					.boxed()
					.collect(Collectors.toList())
			);
	}
	
	@Test
	public void testGeraOperadorAleatorio() {
		Operador operador = geradorAleatorioServiceImpl.gerarOperador();
		assertThat(Operador.values()).contains(operador);
	}

}
