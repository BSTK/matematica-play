package com.caqqi.matematicaplay.desafios.api;

import com.caqqi.matematicaplay.desafios.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.desafios.api.response.DesafioTentativaRespostaResponse;
import com.caqqi.matematicaplay.desafios.core.Mapper;
import com.caqqi.matematicaplay.desafios.domain.entity.DesafioTentativaResposta;
import com.caqqi.matematicaplay.desafios.domain.service.DesafioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tentativas")
public class DesafioTentativaController {

    private final DesafioService desafioService;

    @GetMapping
    public ResponseEntity<List<DesafioTentativaRespostaResponse>> tentativasPorUsuario(
        @RequestParam("apelido") final String usuarioApelido) {

        final List<DesafioTentativaResposta> tentativasResposta = desafioService.ultimasTentivas(usuarioApelido);
        final List<DesafioTentativaRespostaResponse> tentativaRespostaResponse = Mapper
                .list(tentativasResposta, DesafioTentativaRespostaResponse.class);

        return ResponseEntity.ok(tentativaRespostaResponse);
    }

    @PostMapping
    public ResponseEntity<DesafioTentativaRespostaResponse> verificarResposta(
        @RequestBody @Valid final DesafioTentativaRespostaRequest request) {

        final DesafioTentativaResposta tentativaResposta = desafioService.verificarResposta(request);
        final DesafioTentativaRespostaResponse tentativaRespostaResponse = Mapper
                .to(tentativaResposta, DesafioTentativaRespostaResponse.class);

        return ResponseEntity.ok(tentativaRespostaResponse);
    }

}
