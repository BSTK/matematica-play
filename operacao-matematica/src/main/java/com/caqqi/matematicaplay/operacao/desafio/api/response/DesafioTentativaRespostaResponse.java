package com.caqqi.matematicaplay.operacao.desafio.api.response;

import com.caqqi.matematicaplay.operacao.usuario.domain.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class DesafioTentativaRespostaResponse {

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    private int fatorA;
    private int fatorB;
    private int resultado;
    private boolean correta;
    private String operacao;

}
