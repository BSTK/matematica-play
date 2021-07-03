package com.caqqi.matematicaplay.desafios.domain.entity;

import com.caqqi.matematicaplay.desafios.domain.enums.BadgeTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BADGE_CARD")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BadgeCard {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "USUARIO_ID")
    private Long usuarioId;

    @Column(name = "DATA_INSERT")
    private LocalDateTime dataInsert;

    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private BadgeTipo tipo;

    public BadgeCard(final Long usuarioId,
                     final BadgeTipo tipo) {
        this(null, usuarioId, LocalDateTime.now(), tipo);
    }
}
