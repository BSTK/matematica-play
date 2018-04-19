package com.kuiiz.matematicaplay.operacao.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.Operador;
import com.kuiiz.matematicaplay.operacao.service.OperacaoService;

@RunWith(SpringRunner.class)
@WebMvcTest(OperacaoController.class)
public class OperacaoControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private OperacaoService operacaoService;
	
	private JacksonTester<Operacao> json;
	
	
	@Before
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	
	@Test
	public void testOPeracaoAleatoria() throws Exception {
		
		Operacao operacao = new Operacao(10, 10, Operador.SOMA);
		
		given(operacaoService.criaUmaOperacaoAleatoria()).willReturn(operacao);
		
		MockHttpServletResponse response = mvc.perform(get("/operacoes/aleatoria")
												.accept(MediaType.APPLICATION_JSON))
												.andReturn()
												.getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(operacao).getJson());
		
	}

}
