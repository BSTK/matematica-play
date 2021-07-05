package com.caqqi.matematicaplay.gameficacao.domain.repository;

import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<ScoreCard, Long> {

    @Query("SELECT SUM(s.pontos) FROM ScoreCard s WHERE s.usuarioId = :usuarioId")
    Optional<Integer> totalScorePorUsuario(final long usuarioId);

    @Query("SELECT s FROM ScoreCard s WHERE s.usuarioId = :usuarioId ORDER BY s.dataInsert DESC")
    List<ScoreCard> scoresCardsPorUsuario(final long usuarioId);

}
