package com.kuiiz.matematicaplay.operacao.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kuiiz.matematicaplay.operacao.domain.Operador;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GeradorAleatorioServiceTest {
	
	@Autowired
	private GeradorAleatorioService geradorAleatorioService;
	
	
	@Test
	public void testGeraFatoresEntreZeroEMil() {
		
		List<Integer> fatoresAleatorios = IntStream
											.range(0, 1000)
											.map(i -> geradorAleatorioService.gerarFator())
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
		Operador operador = geradorAleatorioService.gerarOperador();
		assertThat(Operador.values()).contains(operador);
	}

}
