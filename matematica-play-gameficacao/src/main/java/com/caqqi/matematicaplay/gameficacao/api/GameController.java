package com.caqqi.matematicaplay.gameficacao.api;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tentativas")
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void tentativa(@RequestBody DesafioTentativaRespostaRequest request) {
        gameService.novaTentativaDoUsuario(request);
    }
}
