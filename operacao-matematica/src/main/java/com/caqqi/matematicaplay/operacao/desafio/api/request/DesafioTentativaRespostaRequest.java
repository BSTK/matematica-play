package com.caqqi.matematicaplay.operacao.desafio.api.request;

import com.caqqi.matematicaplay.operacao.desafio.domain.validation.Contains;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
