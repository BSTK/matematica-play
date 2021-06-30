package com.caqqi.matematicaplay.desafio.domain.entity;

import com.caqqi.matematicaplay.usuario.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DESAFIO_TENTATIVA_RESPOSTA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DesafioTentativaResposta {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @JoinColumn(name = "USUARIO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Column(name = "FATOR_A")
    private int fatorA;

    @Column(name = "FATOR_B")
    private int fatorB;

    @Column(name = "RESULTADO")
    private int resultado;

    @Column(name = "CORRETA")
    private boolean correta;

    @Column(name = "OPERACAO")
    private String operacao;

}
