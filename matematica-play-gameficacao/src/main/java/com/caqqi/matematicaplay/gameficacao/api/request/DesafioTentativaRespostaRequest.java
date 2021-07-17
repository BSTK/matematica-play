package com.caqqi.matematicaplay.gameficacao.api.request;

import com.caqqi.matematicaplay.gameficacao.core.config.Generated;
import com.caqqi.matematicaplay.gameficacao.validation.Contains;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class DesafioTentativaRespostaRequest {

    @NotNull
    long desafioTentativaId;

    @Min(1)
    @Max(99)
    int fatorA;

    @Min(1)
    @Max(99)
    int fatorB;

    @Contains(range = { "+", "-", "*", "/" })
    String operacao;

    @NotNull
    int resposta;

    @NotNull
    boolean correta;

    @NotNull
    long usuarioId;

    @NotNull
    @NotBlank
    String usuarioApelido;

}
