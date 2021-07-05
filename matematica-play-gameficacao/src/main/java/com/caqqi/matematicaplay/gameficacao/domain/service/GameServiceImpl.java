package com.caqqi.matematicaplay.gameficacao.domain.service;

import com.caqqi.matematicaplay.gameficacao.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.gameficacao.domain.entity.BadgeCard;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import com.caqqi.matematicaplay.gameficacao.domain.enums.BadgeTipo;
import com.caqqi.matematicaplay.gameficacao.domain.repository.BadgeRepository;
import com.caqqi.matematicaplay.gameficacao.domain.repository.ScoreRepository;
import com.caqqi.matematicaplay.gameficacao.domain.service.badgeprocessador.BadgeProcessador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private static final Integer TOTAL_PONTOS_ZERO = 0;

    private final BadgeRepository badgeRepository;
    private final ScoreRepository scoreRepository;
    private final List<BadgeProcessador> badgeProcessadores;

    @Override
    public GameResultado novaTentativaDoUsuario(final DesafioTentativaRespostaRequest request) {
        if (Boolean.FALSE.equals(request.isCorreta())) {
            log.info("Tentativa id [ {} ] não está correta! Usuário [ {} ] não ganhou pontos!",
                request.getDesafioTentativaId(),
                request.getUsuarioApelido());

            return new GameResultado(TOTAL_PONTOS_ZERO, Collections.emptyList());
        }

        final ScoreCard scoreCard = new ScoreCard(request.getUsuarioId(), request.getDesafioTentativaId());

        scoreRepository.save(scoreCard);

        log.info("Usuario [ {} ] conseguiu [ {} ] pontos para tentativa id [ {} ]",
            request.getUsuarioApelido(),
            scoreCard.getPontos(),
            request.getDesafioTentativaId());

        final List<BadgeCard> badgesProcessadas = processarBadges(request);
        final List<BadgeTipo> badgesProcessadasPorTipo = badgesProcessadas.stream()
            .map(BadgeCard::getTipo)
            .collect(Collectors.toList());

        return new GameResultado(scoreCard.getPontos(), badgesProcessadasPorTipo);
    }

    private List<BadgeCard> processarBadges(final DesafioTentativaRespostaRequest request) {
        final Optional<Integer> totalScoreOptional = scoreRepository.totalScorePorUsuario(request.getUsuarioId());

        if (totalScoreOptional.isEmpty()) {
            return Collections.emptyList();
        }

        final List<ScoreCard> scoreCards = scoreRepository.scoresCardsPorUsuario(request.getUsuarioId());
        final List<BadgeCard> badgeCards = badgeRepository.badgeCardsPorUsuario(request.getUsuarioId());
        final Set<BadgeTipo> badgeCardsPorTipo = badgeCards.stream().map(BadgeCard::getTipo).collect(Collectors.toSet());

        final Integer totalScore = totalScoreOptional.get();

        final List<BadgeCard> novasBadgeCards = badgeProcessadores.stream()
            .filter(badgeProcessador -> !badgeCardsPorTipo.contains(badgeProcessador.tipo()))
            .map(badgeProcessador -> badgeProcessador.processarBadgeOptional(totalScore, scoreCards, request))
            .flatMap(Optional::stream)
            .map(badgeTipo -> new BadgeCard(request.getUsuarioId(), badgeTipo))
            .collect(Collectors.toList());

        badgeRepository.saveAll(novasBadgeCards);

        return novasBadgeCards;
    }

}
