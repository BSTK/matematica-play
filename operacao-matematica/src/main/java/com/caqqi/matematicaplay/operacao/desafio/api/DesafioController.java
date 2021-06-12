package com.caqqi.matematicaplay.operacao.desafio.api;

import com.caqqi.matematicaplay.operacao.desafio.domain.entidade.Desafio;
import com.caqqi.matematicaplay.operacao.desafio.domain.service.DesafioGeradorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/desafios")
public class DesafioController {

    private final DesafioGeradorService desafioGeradorService;

    @GetMapping("/aleatorio")
    public ResponseEntity<Desafio> desafioAleatorio() {
        final Desafio desafio = desafioGeradorService.gerarDesafioAleatorio();
        log.info("Gerando desafio aleatório: {}", desafio);

        return ResponseEntity.ok(desafio);
    }

}
