package com.caqqi.matematicaplay.operacao.desafio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DESAFIO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Desafio {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "FATOR_A")
    private int fatorA;

    @Column(name = "FATOR_B")
    private int fatorB;

    @Column(name = "ALTERNATIVAS")
    private int[] alternativas;

    @Column(name = "OPERACAO")
    private String operacao;

}
