package com.caqqi.matematicaplay.desafios.domain.service;

import com.caqqi.matematicaplay.desafios.domain.entity.DesafioTentativaResposta;
import com.caqqi.matematicaplay.desafios.domain.service.dto.DesafioTentativaRespostaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class GameficacaoServiceCliente {

    private static final String ENDPOINT_API_GAMINIFACAO_TENTITIVAS = "/api/v1/tentativas";

    private final String gamificacaoUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public GameficacaoServiceCliente(@Value("${microservico.gamificacao.url}")
                                     final String gamificacaoUrl,
                                     final RestTemplateBuilder templateBuilder) {
        this.gamificacaoUrl = gamificacaoUrl;
        this.restTemplate = templateBuilder.build();
    }

    public Boolean enviarTentativa(final DesafioTentativaResposta tentativaResposta) {
        try {
            final DesafioTentativaRespostaDto tentativaRespostaDto = new DesafioTentativaRespostaDto(
                tentativaResposta.getId(),
                tentativaResposta.getFatorA(),
                tentativaResposta.getFatorB(),
                tentativaResposta.getOperacao(),
                tentativaResposta.getResultado(),
                tentativaResposta.isCorreta(),
                tentativaResposta.getUsuario().getId(),
                tentativaResposta.getUsuario().getApelido());

            final String urlRequest = gamificacaoUrl.concat(ENDPOINT_API_GAMINIFACAO_TENTITIVAS);
            final ResponseEntity<Void> response = restTemplate.postForEntity(urlRequest, tentativaRespostaDto, Void.class);

            log.info("Gamificação response: [ {} ], status code: [ {} ]",
                response.getBody(),
                response.getStatusCode());

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception ex) {
            log.error("Não foi possivel enviar tentativa de reposta para o serviço de Gamificação.", ex);
            return Boolean.FALSE;
        }
    }
}
