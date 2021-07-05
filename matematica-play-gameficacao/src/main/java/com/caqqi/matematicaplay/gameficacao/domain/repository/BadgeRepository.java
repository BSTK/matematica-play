package com.caqqi.matematicaplay.gameficacao.domain.repository;

import com.caqqi.matematicaplay.gameficacao.domain.entity.BadgeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BadgeRepository extends JpaRepository<BadgeCard, Long> {

    @Query("SELECT b FROM BadgeCard b WHERE b.usuarioId = :usuarioId ORDER BY b.dataInsert DESC")
    List<BadgeCard> badgeCardsPorUsuario(final long usuarioId);

}
