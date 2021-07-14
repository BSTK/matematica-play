package com.caqqi.matematicaplay.desafios.usuario.domain.service;

import com.caqqi.matematicaplay.desafios.usuario.domain.entity.Usuario;
import com.caqqi.matematicaplay.desafios.usuario.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> usuariosPorIds(@PathVariable("usuariosId") final List<Long> usuariosId) {
        final Iterable<Usuario> usuariosPorId = usuarioRepository.findAllById(usuariosId);
        return StreamSupport
            .stream(usuariosPorId.spliterator(), false)
            .collect(Collectors.toList());
    }
}
