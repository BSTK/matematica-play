package com.caqqi.matematicaplay.gameficacao.api;

import com.caqqi.matematicaplay.gameficacao.domain.LiredesBoardLinha;
import com.caqqi.matematicaplay.gameficacao.domain.service.LiredesBoardService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LiredesBoardController.class)
public class LiredesBoardControllerTest {

    private static final String ENDPOINT_API_V1_LIDERES = "/api/v1/lideres";

    @MockBean
    private LiredesBoardService liredesBoardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar o LiredesBoard atual")
    public void deveRetornarOLiredesBoardAtual() throws Exception {
        List<LiredesBoardLinha> liredesBoard = List.of(
            new LiredesBoardLinha(10L, 20L),
            new LiredesBoardLinha(20L, 30L),
            new LiredesBoardLinha(30L, 50L));

        when(liredesBoardService.getLiredesBoardAtual()).thenReturn(liredesBoard);

        mockMvc.perform(
            get(ENDPOINT_API_V1_LIDERES))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()")
                .value(liredesBoard.size()));
    }

}
