package com.kuiiz.matematicaplay.operacao.controller;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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
import com.kuiiz.matematicaplay.operacao.controller.TentativaSolucaoController.ResultadoResponse;
import com.kuiiz.matematicaplay.operacao.domain.Operacao;
import com.kuiiz.matematicaplay.operacao.domain.Operador;
import com.kuiiz.matematicaplay.operacao.domain.TentativaSolucao;
import com.kuiiz.matematicaplay.operacao.domain.Usuario;
import com.kuiiz.matematicaplay.operacao.service.OperacaoService;

@RunWith(SpringRunner.class)
@WebMvcTest(TentativaSolucaoController.class)
public class TentativaSolucaoControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OperacaoService operacaoService;

	private JacksonTester<TentativaSolucao> jsonResult;
	private JacksonTester<ResultadoResponse> jsonResponse;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	
	@Test
	public void testPostResultadoCorreto() throws Exception {
		postGenerico(true);
	}
	
	@Test
	public void testPostResultadoIncorreto() throws Exception {
		postGenerico(false);
	}
	
	/**
	 * Post Generico
	 * @param correto
	 * @throws Exception 
	 */
	private void postGenerico(final boolean correto) throws Exception {
		
		given(operacaoService.verificaTentativa(any(TentativaSolucao.class))).willReturn(correto);
		
		TentativaSolucao solucao = new TentativaSolucao(
				new Usuario("John"), 
				new Operacao(100, 100, Operador.SOMA), 200);
		
		MockHttpServletResponse response = mvc.perform(post("/resultados")
								.contentType(MediaType.APPLICATION_JSON)
								.content(jsonResult.write(solucao).getJson()))
						.andReturn()
						.getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString())
			.isEqualTo(jsonResponse.write(new ResultadoResponse(correto)).getJson());
		
	}

}
