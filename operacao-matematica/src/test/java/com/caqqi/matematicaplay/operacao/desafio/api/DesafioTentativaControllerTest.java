package com.caqqi.matematicaplay.operacao.desafio.api;

import com.caqqi.matematicaplay.operacao.core.Mapper;
import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.api.response.DesafioTentativaRespostaResponse;
import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.DesafioTentativaResposta;
import com.caqqi.matematicaplay.operacao.desafio.domain.service.DesafioService;
import com.caqqi.matematicaplay.operacao.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.caqqi.matematicaplay.operacao.desafio.domain.DesafioOperacao.ADICAO;
import static com.caqqi.matematicaplay.operacao.desafio.domain.DesafioOperacao.SUBTRACAO;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureJsonTesters
@ExtendWith(SpringExtension.class)
@WebMvcTest(DesafioTentativaController.class)
public class DesafioTentativaControllerTest {

    private static final String ENDPOINT_V1_TENTATIVAS = "/v1/tentativas";

    @MockBean
    private DesafioService desafioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DesafioTentativaRespostaRequest> jsonTentativaRespostaRequest;

    @Test
    @DisplayName("Deve postar uma tentativa com resultado correto")
    public void devePostarUmaTentativaComResultadoCorreto() throws Exception {
        final Usuario usuario = new Usuario(1L, "UsuarioAA");
        final Long tentativaId = 1L;

        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            50, 50, 100, ADICAO.getOperador(), "Jhon");

        final DesafioTentativaResposta respostaEsperada = new DesafioTentativaResposta(
            tentativaId, usuario, 50, 50, 100, true, ADICAO.getOperador());

        final DesafioTentativaRespostaResponse respostaEsperadaResponse = Mapper.to(respostaEsperada, DesafioTentativaRespostaResponse.class);

        given(desafioService
            .verificarResposta(eq(request)))
            .willReturn(respostaEsperada);

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(respostaEsperadaResponse.getId()))
            .andExpect(jsonPath("$.fatorA").value(respostaEsperadaResponse.getFatorA()))
            .andExpect(jsonPath("$.fatorB").value(respostaEsperadaResponse.getFatorB()))
            .andExpect(jsonPath("$.resultado").value(respostaEsperadaResponse.getResultado()))
            .andExpect(jsonPath("$.correta").value(respostaEsperadaResponse.isCorreta()))
            .andExpect(jsonPath("$.operacao").value(respostaEsperadaResponse.getOperacao()));
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Operação Inválida)")
    public void devePostarUmaTentativaComRequestInvalido_OperacaoInvalida() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            50, 50, 100, "#", "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorA negativo)")
    public void devePostarUmaTentativaComRequestInvalido_FatorANegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            -1, 50, 100, SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorA maior que 99)")
    public void devePostarUmaTentativaComRequestInvalido_FatorAMaiorQue99() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            999, 50, 100, SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorB negativo)")
    public void devePostarUmaTentativaComRequestInvalido_FatorBNegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, -1, 100, SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (FatorB maior que 99)")
    public void devePostarUmaTentativaComRequestInvalido_FatorBMaiorQue99() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 999, 100, SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    public void devePostarUmaTentativaComRequestInvalido_RespostaNegativo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 999, -1, SUBTRACAO.getOperador(), "Jhon");

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    public void devePostarUmaTentativaComRequestInvalido_ApelidoNulo() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 10, -1, SUBTRACAO.getOperador(), null);

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    public void devePostarUmaTentativaComRequestInvalido_ApelidoVazio() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 10, 0, SUBTRACAO.getOperador(), "");

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com request inválido (Resposta negativo)")
    public void devePostarUmaTentativaComRequestInvalido_ApelidoBranco() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            10, 10, 0, SUBTRACAO.getOperador(), "  ");

        this.mockMvc.perform(
            post(ENDPOINT_V1_TENTATIVAS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andExpect(status().isBadRequest());
    }

}
