package com.caqqi.matematicaplay.desafios.usuario.api;

import com.caqqi.matematicaplay.desafios.api.response.UsuarioResponse;
import com.caqqi.matematicaplay.desafios.core.Mapper;
import com.caqqi.matematicaplay.desafios.usuario.domain.entity.Usuario;
import com.caqqi.matematicaplay.desafios.usuario.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<UsuarioResponse> usuarioPorApelido(@RequestParam("apelido") final String apelido) {
        final Usuario usuario = usuarioService.usuarioPorApelido(apelido);
        final UsuarioResponse usuarioResponse = Mapper.to(usuario, UsuarioResponse.class);
        return ResponseEntity.ok(usuarioResponse);
    }

    @GetMapping("/{usuariosId}")
    public ResponseEntity<List<UsuarioResponse>> usuariosPorIds(@PathVariable("usuariosId") final List<Long> usuariosId) {
        final List<Usuario> usuariosPorIdLista = usuarioService.usuariosPorIds(usuariosId);
        final List<UsuarioResponse> usuariosPorIdResponse = Mapper.list(usuariosPorIdLista, UsuarioResponse.class);
        return ResponseEntity.ok(usuariosPorIdResponse);
    }
}
