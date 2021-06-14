package com.caqqi.matematicaplay.operacao.desafio.api;

import com.caqqi.matematicaplay.operacao.desafio.domain.service.DesafioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tentativa")
public class DesafioTentativaController {

    private final DesafioService desafioService;


}
