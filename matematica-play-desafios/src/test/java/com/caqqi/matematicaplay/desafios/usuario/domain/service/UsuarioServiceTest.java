package com.caqqi.matematicaplay.desafios.usuario.domain.service;

import com.caqqi.matematicaplay.desafios.usuario.domain.entity.Usuario;
import com.caqqi.matematicaplay.desafios.usuario.domain.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuarios por id")
    public void deveRetornarUmaListaDeUsuariosPorId() {
        List<Usuario> usuarios = List.of(
            new Usuario("apelido-a"),
            new Usuario("apelido-b"),
            new Usuario("apelido-c"));

        when(usuarioRepository.findAllById(anyList())).thenReturn(usuarios);

        List<Usuario> usuariosPorId = usuarioService.usuariosPorIds(List.of(1L, 2L, 3L));

        Assertions.assertThat(usuariosPorId)
            .isNotNull()
            .isNotEmpty()
            .hasSize(usuarios.size())
            .containsAll(usuarios);
    }

}
