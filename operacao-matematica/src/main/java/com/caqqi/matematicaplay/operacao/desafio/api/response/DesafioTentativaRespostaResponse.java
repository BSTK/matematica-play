package com.caqqi.matematicaplay.operacao.desafio.api.response;

import com.caqqi.matematicaplay.operacao.usuario.Usuario;
import lombok.Data;

@Data
public class DesafioTentativaRespostaResponse {

    private Long id;
    private Usuario usuario;
    private int fatorA;
    private int fatorB;
    private int resultado;
    private boolean correta;
    private String operacao;

}
