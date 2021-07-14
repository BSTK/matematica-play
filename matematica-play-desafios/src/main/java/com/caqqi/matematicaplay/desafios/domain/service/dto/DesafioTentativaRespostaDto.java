package com.caqqi.matematicaplay.desafios.domain.service.dto;

import com.caqqi.matematicaplay.desafios.domain.validation.Contains;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class DesafioTentativaRespostaDto {

    @NotNull
    private final long desafioTentativaId;

    @Min(1)
    @Max(99)
    private final int fatorA;

    @Min(1)
    @Max(99)
    private final int fatorB;

    @Contains(range = { "+", "-", "*", "/" })
    private final String operacao;

    @NotNull
    private final int resposta;

    @NotNull
    private final boolean correta;

    @NotNull
    private final long usuarioId;

    @NotNull
    @NotBlank
    private final String usuarioApelido;
}
