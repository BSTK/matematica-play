package com.caqqi.matematicaplay.gameficacao.api;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import com.caqqi.matematicaplay.gameficacao.domain.service.GameResultado;
import com.caqqi.matematicaplay.gameficacao.domain.service.GameService;
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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    private static final String ENDPOINT_API_V1_TENTATIVAS = "/api/v1/tentativas";

    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("Deve postar uma nova tentativa do usuario")
    public void devePostarUmaNovaTentativaDoUsuario() throws Exception {
        final DesafioTentativaRespostaRequest request = new DesafioTentativaRespostaRequest();
        request.setFatorA(10);
        request.setFatorB(10);
        request.setOperacao("+");
        request.setCorreta(Boolean.TRUE);

        when(gameService.novaTentativaDoUsuario(request))
            .thenReturn(new GameResultado(10, List.of(BadgeTipo.BRONZE)));

        mockMvc.perform(
            post(ENDPOINT_API_V1_TENTATIVAS)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk());
    }

}
