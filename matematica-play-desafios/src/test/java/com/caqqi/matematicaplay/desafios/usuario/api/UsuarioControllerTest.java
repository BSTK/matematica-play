package com.caqqi.matematicaplay.desafios.usuario.api;

import com.caqqi.matematicaplay.desafios.usuario.domain.entity.Usuario;
import com.caqqi.matematicaplay.desafios.usuario.domain.service.UsuarioService;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    private static final String ENDPOINT_API_V1_USUARIOS = "/api/v1/usuarios";

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar uma lista de usuarios por id")
    void deveRetornarUmaListaDeUsuariosPorId() throws Exception {
        List<Usuario> usuarios = List.of(
            new Usuario("apelido-a"),
            new Usuario("apelido-b"),
            new Usuario("apelido-c"));

        when(usuarioService.usuariosPorIds(anyList())).thenReturn(usuarios);

        mockMvc.perform(
            get(ENDPOINT_API_V1_USUARIOS.concat("/{usuariosId}"), "1,2,3"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(usuarios.size()))
            .andExpect(jsonPath("$.[0].apelido").value("apelido-a"))
            .andExpect(jsonPath("$.[1].apelido").value("apelido-b"))
            .andExpect(jsonPath("$.[2].apelido").value("apelido-c"));
    }

    @Test
    @DisplayName("Deve retornar um usuário por apelido")
    void deveRetornarUmUsuarioPorApelido() throws Exception {
        when(usuarioService.usuarioPorApelido("apelido-a"))
            .thenReturn(new Usuario("apelido-a"));

        mockMvc.perform(
            get(ENDPOINT_API_V1_USUARIOS)
                .param("apelido", "apelido-a"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.apelido").value("apelido-a"));
    }

}
