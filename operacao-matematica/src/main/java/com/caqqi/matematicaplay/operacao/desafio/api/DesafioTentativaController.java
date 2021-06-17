package com.caqqi.matematicaplay.operacao.desafio.api;

import com.caqqi.matematicaplay.operacao.core.Mapper;
import com.caqqi.matematicaplay.operacao.desafio.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.operacao.desafio.api.response.DesafioTentativaRespostaResponse;
import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.DesafioTentativaResposta;
import com.caqqi.matematicaplay.operacao.desafio.domain.service.DesafioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tentativas")
public class DesafioTentativaController {

    private final DesafioService desafioService;

    @PostMapping
    public ResponseEntity<DesafioTentativaRespostaResponse> verificarResposta(@RequestBody @Valid final DesafioTentativaRespostaRequest request) {
        DesafioTentativaResposta tentativaResposta = desafioService.verificarResposta(request);
        DesafioTentativaRespostaResponse tentativaRespostaResponse = Mapper.to(tentativaResposta, DesafioTentativaRespostaResponse.class);
        return ResponseEntity.ok(tentativaRespostaResponse);
    }

}
