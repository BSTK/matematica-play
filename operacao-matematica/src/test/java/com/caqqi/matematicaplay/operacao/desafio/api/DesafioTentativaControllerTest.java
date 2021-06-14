package com.caqqi.matematicaplay.operacao.desafio.api;

import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.caqqi.matematicaplay.operacao.desafio.domain.DesafioOperacao.ADICAO;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@AutoConfigureJsonTesters
@ExtendWith(SpringExtension.class)
@WebMvcTest(DesafioTentativaController.class)
public class DesafioTentativaControllerTest {

    @MockBean
    private DesafioService desafioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DesafioTentativaResposta> jsonTentativaResposta;

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

        given(desafioService
            .verificarResposta(eq(request)))
            .willReturn(respostaEsperada);

        MockHttpServletResponse response = mockMvc.perform(
            post("/tentativa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTentativaRespostaRequest.write(request).getJson()))
            .andReturn()
            .getResponse();

        then(response.getStatus())
            .isEqualTo(HttpStatus.OK.value());

        then(response.getContentAsString())
            .isEqualTo(jsonTentativaResposta.write(respostaEsperada).getJson());
    }

    @Test
    @DisplayName("Deve postar uma tentativa com resultado errado")
    public void devePostarUmaTentativaComResultadoErrado() {

    }

}
