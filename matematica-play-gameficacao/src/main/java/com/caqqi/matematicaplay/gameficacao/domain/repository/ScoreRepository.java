package com.caqqi.matematicaplay.gameficacao.domain.repository;

import com.caqqi.matematicaplay.gameficacao.domain.LiredesBoardLinha;
import com.caqqi.matematicaplay.gameficacao.domain.entity.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<ScoreCard, Long> {

    @Query("SELECT SUM(s.pontos) FROM ScoreCard s WHERE s.usuarioId = :usuarioId GROUP BY s.usuarioId")
    Optional<Integer> totalScorePorUsuario(@Param("usuarioId") final Long usuarioId);

    @Query("SELECT s FROM ScoreCard s WHERE s.usuarioId = :usuarioId ORDER BY s.dataInsert DESC")
    List<ScoreCard> scoresCardsPorUsuario(@Param("usuarioId") final Long usuarioId);

    @Query("SELECT NEW com.caqqi.matematicaplay.gameficacao.domain.LiredesBoardLinha("
        + " S.usuarioId, "
        + " SUM(S.pontos)) "
        + " FROM ScoreCard s "
        + " GROUP BY s.usuarioId "
        + " ORDER BY sum(s.pontos) DESC")
    List<LiredesBoardLinha> obterOs10Scores();
}
