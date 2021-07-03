package com.caqqi.matematicaplay.desafios.api.request;

import com.caqqi.matematicaplay.desafios.domain.validation.Contains;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull
    int resposta;

    @Contains(range = { "+", "-", "*", "/" })
    String operacao;

    @NotNull
    @NotBlank
    String apelido;

}
