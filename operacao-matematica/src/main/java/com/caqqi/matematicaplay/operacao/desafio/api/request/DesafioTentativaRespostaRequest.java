package com.caqqi.matematicaplay.operacao.desafio.api.request;

import com.caqqi.matematicaplay.operacao.desafio.domain.validation.Contains;
import lombok.Value;

import javax.validation.constraints.*;

@Value
public class DesafioTentativaRespostaRequest {

    @Min(1)
    @Max(99)
    int fatorA;

    @Min(1)
    @Max(99)
    int fatorB;

    @PositiveOrZero
    int resposta;

    @Contains(range = { "+", "-", "*", "/" })
    String operacao;

    @NotNull
    @NotBlank
    String apelido;

}
