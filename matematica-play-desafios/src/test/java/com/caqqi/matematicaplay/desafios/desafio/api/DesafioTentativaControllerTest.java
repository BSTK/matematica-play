package com.caqqi.matematicaplay.desafios.desafio.api;

import com.caqqi.matematicaplay.desafios.api.DesafioTentativaController;
import com.caqqi.matematicaplay.desafios.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.desafios.api.response.DesafioTentativaRespostaResponse;
import com.caqqi.matematicaplay.desafios.core.Mapper;
import com.caqqi.matematicaplay.desafios.domain.DesafioOperacao;
import com.caqqi.matematicaplay.desafios.domain.entity.DesafioTentativaResposta;
import com.caqqi.matematicaplay.desafios.domain.service.DesafioService;
import com.caqqi.matematicaplay.desafios.usuario.domain.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DesafioTentativaController.class)
class DesafioTentativaControllerTest {

    private static final String ENDPOINT_API_V1_TENTATIVAS = "/api/v1/tentativas";

    @MockBean
    private DesafioService desafioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("Deve postar uma tentativa com resultado correto")
    void devePostarUmaTentativaComResultadoCorreto() throws Exception {
        final Usuario usuario = new Usuario(1L, "UsuarioAA");
        final Long tentativaId = 1L;

        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            50, 50, 100, DesafioOperacao.ADICAO.getOperador(), "Jhon");

        final DesafioTentativaResposta respostaEsperada = new DesafioTentativaResposta(
            tentativaId, usuario, 50, 50, 100, true, DesafioOperacao.ADICAO.getOperador());

        final DesafioTentativaRespostaResponse respostaEsperadaResponse = Mapper.to(respostaEsperada, DesafioTentativaRespostaResponse.class);

        given(desafioService
            .verificarResposta(request))
            .willReturn(respostaEsperada);

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.fatorA").value(respostaEsperadaResponse.getFatorA()))
            .andExpect(jsonPath("$.fatorB").value(respostaEsperadaResponse.getFatorB()))
            .andExpect(jsonPath("$.resultado").value(respostaEsperadaResponse.getResultado()))
            .andExpect(jsonPath("$.correta").value(respostaEsperadaResponse.isCorreta()))
            .andExpect(jsonPath("$.operacao").value(respostaEsperadaResponse.getOperacao()));
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Operação Inválida)")
    void devePostarUmaTentativaComRequestInvalido_OperacaoInvalida() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            50, 50, 100, "#", "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorA negativo)")
    void devePostarUmaTentativaComRequestInvalido_FatorANegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            -1, 50, 100, DesafioOperacao.SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorA maior que 99)")
    void devePostarUmaTentativaComRequestInvalido_FatorAMaiorQue99() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            999, 50, 100, DesafioOperacao.SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorB negativo)")
    void devePostarUmaTentativaComRequestInvalido_FatorBNegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, -1, 100, DesafioOperacao.SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorB maior que 99)")
    void devePostarUmaTentativaComRequestInvalido_FatorBMaiorQue99() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 999, 100, DesafioOperacao.SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    void devePostarUmaTentativaComRequestInvalido_RespostaNegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 999, -1, DesafioOperacao.SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    void devePostarUmaTentativaComRequestInvalido_ApelidoNulo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 10, -1, DesafioOperacao.SUBTRACAO.getOperador(), null);

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    void devePostarUmaTentativaComRequestInvalido_ApelidoVazio() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 10, 0, DesafioOperacao.SUBTRACAO.getOperador(), "");

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    void devePostarUmaTentativaComRequestInvalido_ApelidoBranco() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 10, 0, DesafioOperacao.SUBTRACAO.getOperador(), "  ");

        this.mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

}
