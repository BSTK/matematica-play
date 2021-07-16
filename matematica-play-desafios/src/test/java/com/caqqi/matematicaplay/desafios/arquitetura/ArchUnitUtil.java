package com.caqqi.matematicaplay.desafios.arquitetura;

abstract class ArchUnitUtil {

    static class Core {
        static final String PACOTE_PADRAO = "com.caqqi.matematicaplay.operacao";

        static final String POSFIXO_SERVICE = "Service";
        static final String POSFIXO_REPOSITORY = "Repository";
        static final String POSFIXO_REPOSITORY_IMPL = "RepositoryImpl";
    }

    static class Desafio {
        static final String PACOTE_BASE = "com.caqqi.matematicaplay.operacao.desafio";
        static final String PACOTE_API = "com.caqqi.matematicaplay.operacao.desafio.api";
        static final String PACOTE_DOMAIN = "com.caqqi.matematicaplay.operacao.desafio.domain";
    }

}
