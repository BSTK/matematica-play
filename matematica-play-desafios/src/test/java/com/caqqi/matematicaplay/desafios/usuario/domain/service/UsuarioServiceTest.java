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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
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
            .containsAll(usuarios)
            .hasSize(usuarios.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuarios vazia quando não houver usuario cadastrados com ids informados")
    public void deveRetornarUmaListaDeUsuariosVaziaQuandoNaoHouverUsuarioCadastradosComIdsInformados() {
        when(usuarioRepository.findAllById(anyList())).thenReturn(Collections.emptyList());

        List<Usuario> usuariosPorId = usuarioService.usuariosPorIds(List.of(1L, 2L, 3L));

        Assertions.assertThat(usuariosPorId)
            .isNotNull()
            .isEmpty();
    }

    @Test
    @DisplayName("Deve retornar um usuário por apelido")
    public void deveRetornarUmUsuarioPorApelido() {
        when(usuarioRepository.buscarPorApelido(anyString()))
            .thenReturn(Optional.of(new Usuario("apelido-a")));

        Usuario usuario = usuarioService.usuarioPorApelido("apelido-a");

        Assertions.assertThat(usuario).isNotNull();
        Assertions.assertThat(usuario.getApelido()).isEqualTo("apelido-a");
    }

    @Test
    @DisplayName("Deve lancar excesão ao buscar um usuário por apelido")
    public void deveLancarExcesaoAoBuscarUmUsuarioPorApelido() {
        when(usuarioRepository.buscarPorApelido(anyString())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> usuarioService.usuarioPorApelido("apelido-a"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Não existe usuário para o apelido: apelido-a");
    }

}
