package com.caqqi.matematicaplay.desafios.domain.service;

import com.caqqi.matematicaplay.desafios.api.request.DesafioTentativaRespostaRequest;
import com.caqqi.matematicaplay.desafios.domain.DesafioOperacao;
import com.caqqi.matematicaplay.desafios.domain.entity.DesafioTentativaResposta;
import com.caqqi.matematicaplay.desafios.domain.repository.DesafioTentativaRespostaRepository;
import com.caqqi.matematicaplay.desafios.usuario.domain.entity.Usuario;
import com.caqqi.matematicaplay.desafios.usuario.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DesafioServiceImpl implements DesafioService {

    private final UsuarioRepository usuarioRepository;
    private final GameficacaoServiceCliente gameficacaoServiceCliente;
    private final DesafioTentativaRespostaRepository desafioTentativaRespostaRepository;

    @Override
    public List<DesafioTentativaResposta> ultimasTentivas(final String usuarioApelido) {
        return desafioTentativaRespostaRepository.findTop10ByUsuarioApelidoOrderByIdDesc(usuarioApelido);
    }

    @Override
    public DesafioTentativaResposta verificarResposta(final DesafioTentativaRespostaRequest tentativaRequest) {
        final Usuario usuario = usuarioRepository
                .buscarPorApelido(tentativaRequest.getApelido())
                .orElseGet(() -> {
                    log.info("Criando um novo usuário com apelido: {}", tentativaRequest.getApelido());

                    return usuarioRepository.save(
                        new Usuario(tentativaRequest.getApelido())
                    );
                });

        final int resultadoTentativa = DesafioOperacao
                                            .of(tentativaRequest.getOperacao())
                                            .execute(tentativaRequest.getFatorA(), tentativaRequest.getFatorB());

        final boolean respostaCorreta = tentativaRequest.getResposta() == resultadoTentativa;

        final DesafioTentativaResposta desafioTentativaResposta = new DesafioTentativaResposta(
                null,
                usuario,
                tentativaRequest.getFatorA(),
                tentativaRequest.getFatorB(),
                resultadoTentativa,
                respostaCorreta,
                tentativaRequest.getOperacao());

        final DesafioTentativaResposta tentativaRespostaSalva = desafioTentativaRespostaRepository.save(desafioTentativaResposta);
        final Boolean gameficacaoResponse = gameficacaoServiceCliente.enviarTentativa(tentativaRespostaSalva);

        log.info("Request Ok para serviço de Gameficação: {}", gameficacaoResponse);

        return tentativaRespostaSalva;
    }

}
