package com.caqqi.matematicaplay.gameficacao.domain.service;

import com.caqqi.matematicaplay.gameficacao.domain.LiredesBoardLinha;
import com.caqqi.matematicaplay.gameficacao.domain.repository.BadgeRepository;
import com.caqqi.matematicaplay.gameficacao.domain.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LiredesBoardServiceImpl implements LiredesBoardService {

    private final ScoreRepository scoreRepository;
    private final BadgeRepository badgeRepository;

    @Override
    public List<LiredesBoardLinha> getLiredesBoardAtual() {
        final List<LiredesBoardLinha> os10Scores = scoreRepository.obterOs10Scores();

        if (CollectionUtils.isEmpty(os10Scores)) {
            return Collections.emptyList();
        }

        return os10Scores
            .stream()
            .map(linha -> {
                List<String> badgesDescricao = badgeRepository
                    .badgeCardsPorUsuario(linha.getUsuarioId())
                    .stream()
                    .map(badgeCard -> badgeCard.getTipo().getDescricao())
                    .collect(Collectors.toList());

                return linha.withBadges(badgesDescricao);
            }).collect(Collectors.toList());
    }

}
