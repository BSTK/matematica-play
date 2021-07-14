package com.caqqi.matematicaplay.gameficacao.api;

import com.caqqi.matematicaplay.gameficacao.api.response.LiredesBoardLinhaResponse;
import com.caqqi.matematicaplay.gameficacao.domain.LiredesBoardLinha;
import com.caqqi.matematicaplay.gameficacao.domain.service.LiredesBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.caqqi.matematicaplay.gameficacao.core.Mapper.list;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lideres")
public class LiredesBoardController {

    private final LiredesBoardService liredesBoardService;

    @GetMapping
    public ResponseEntity<List<LiredesBoardLinhaResponse>> liredesBoard() {
        final List<LiredesBoardLinha> liredesBoardAtual = liredesBoardService.getLiredesBoardAtual();
        final List<LiredesBoardLinhaResponse> liredesBoardAtualResponse =
            list(liredesBoardAtual, LiredesBoardLinhaResponse.class);
        return ResponseEntity.ok(liredesBoardAtualResponse);
    }

}
