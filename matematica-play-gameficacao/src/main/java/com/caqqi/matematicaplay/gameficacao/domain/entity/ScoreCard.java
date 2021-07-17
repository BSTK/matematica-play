package com.caqqi.matematicaplay.gameficacao.domain.entity;

import com.caqqi.matematicaplay.gameficacao.core.config.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SCORE_CARD")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ScoreCard {

    private static final int PONTOS_DEFAULT = 10;

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "USUARIO_ID")
    private Long usuarioId;

    @Column(name = "DESAFIO_TENTAIVA_ID")
    private Long desafioTentaivaId;

    @Column(name = "DATA_INSERT")
    private LocalDateTime dataInsert;

    @Column(name = "PONTOS")
    private int pontos;

    public ScoreCard(final Long usuarioId,
                     final Long desafioTentaivaId) {
        this(null, usuarioId, desafioTentaivaId, LocalDateTime.now(), PONTOS_DEFAULT);
    }
}
