package com.caqqi.matematicaplay.desafios.usuario.domain.service;

import com.caqqi.matematicaplay.desafios.usuario.domain.entity.Usuario;
import com.caqqi.matematicaplay.desafios.usuario.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> usuariosPorIds(final List<Long> usuariosId) {
        final Iterable<Usuario> usuariosPorId = usuarioRepository.findAllById(usuariosId);
        return StreamSupport
            .stream(usuariosPorId.spliterator(), false)
            .collect(Collectors.toList());
    }

    public Usuario usuarioPorApelido(final String apelido) {
        return usuarioRepository.buscarPorApelido(apelido)
            .orElseThrow(() -> {
                final String menssagemException = String.format("Não existe usuário para o apelido: %s", apelido);
                log.warn(menssagemException);

                throw new IllegalArgumentException(menssagemException);
            });
    }
}
