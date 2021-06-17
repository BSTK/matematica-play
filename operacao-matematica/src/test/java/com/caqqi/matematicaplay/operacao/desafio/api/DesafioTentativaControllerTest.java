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

import java.io.IOException;

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
            50, 50, 100, SUBTRACAO.getOperador(), "Jhon");

        final DesafioTentativaResposta respostaEsperada = new DesafioTentativaResposta(
            tentativaId, usuario, 50, 50, 100, true, ADICAO.getOperador());

        final DesafioTentativaRespostaResponse respostaEsperadaResponse = Mapper.to(respostaEsperada, DesafioTentativaRespostaResponse.class);

        given(desafioService
            .verificarResposta(eq(request)))
            .willReturn(respostaEsperada);

        this.mockMvc.perform(
            post("/v1/tentativas")
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
    @DisplayName("Deve postar uma tentativa com resultado errado")
    public void devePostarUmaTentativaComResultadoErrado() throws Exception {
        final Usuario usuario = new Usuario(1L, "UsuarioAA");
        final Long tentativaId = 1L;

        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest(
            50, 50, 1000, SUBTRACAO.getOperador(), "Jhon");

        final DesafioTentativaResposta respostaEsperada = new DesafioTentativaResposta(
            tentativaId, usuario, 50, 50, 100, false, ADICAO.getOperador());

        final DesafioTentativaRespostaResponse respostaEsperadaResponse = Mapper.to(respostaEsperada, DesafioTentativaRespostaResponse.class);

        given(desafioService
            .verificarResposta(eq(request)))
            .willReturn(respostaEsperada);

        this.mockMvc.perform(
            post("/v1/tentativas")
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

}
