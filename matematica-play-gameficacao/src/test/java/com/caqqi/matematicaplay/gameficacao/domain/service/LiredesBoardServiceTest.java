package com.caqqi.matematicaplay.gameficacao.domain.service;

import com.caqqi.matematicaplay.gameficacao.domain.LiredesBoardLinha;
import com.caqqi.matematicaplay.gameficacao.domain.repository.BadgeRepository;
import com.caqqi.matematicaplay.gameficacao.domain.repository.ScoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LiredesBoardServiceTest {

    private LiredesBoardService liredesBoardService;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private BadgeRepository badgeRepository;


    @BeforeEach
    public void setUp() {
        liredesBoardService = new LiredesBoardServiceImpl(
            scoreRepository,
            badgeRepository
        );
    }

    @Test
    @DisplayName("Deve retornar uma lista de LiredesBoardLinha válida")
    public void deveUmaListaDeLiredesBoardLinhaValida() {
        final List<LiredesBoardLinha> liredesBoardLinhas = List.of(
            new LiredesBoardLinha(1L, 2L),
            new LiredesBoardLinha(10L, 20L),
            new LiredesBoardLinha(30L, 40L),
            new LiredesBoardLinha(100L, 200L));

        when(scoreRepository.obterOs10Scores()).thenReturn(liredesBoardLinhas);

        final List<LiredesBoardLinha> liredesBoardAtual = liredesBoardService.getLiredesBoardAtual();

        verify(scoreRepository, times(1)).obterOs10Scores();
        verify(badgeRepository, times(liredesBoardLinhas.size())).badgeCardsPorUsuario(anyLong());

        Assertions.assertThat(liredesBoardAtual)
            .isNotNull()
            .isNotEmpty()
            .hasSize(liredesBoardLinhas.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando scoreRepository não retornar resultado dos 10 primeiros scores [NULO]")
    public void deveRetornarUmaListaVaziaQuandoScorerepositoryNaoRetornarResultadoDos10PrimeirosScoresNull() {
        when(scoreRepository.obterOs10Scores()).thenReturn(null);

        final List<LiredesBoardLinha> liredesBoardAtual = liredesBoardService.getLiredesBoardAtual();

        Assertions.assertThat(liredesBoardAtual)
            .isNotNull()
            .isEmpty();
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando scoreRepository não retornar resultado dos 10 primeiros scores [VAZIO]")
    public void deveRetornarUmaListaVaziaQuandoScorerepositoryNaoRetornarResultadoDos10PrimeirosScoresVazio() {
        when(scoreRepository.obterOs10Scores()).thenReturn(Collections.emptyList());

        final List<LiredesBoardLinha> liredesBoardAtual = liredesBoardService.getLiredesBoardAtual();

        Assertions.assertThat(liredesBoardAtual)
            .isNotNull()
            .isEmpty();
    }

}
