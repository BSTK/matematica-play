package com.caqqi.matematicaplay.desafios.arquitetura;

public abstract class ArchUnitUtil {

    static class Core {
        public static final String PACOTE_PADRAO = "com.caqqi.matematicaplay.operacao";

        public static final String POSFIXO_SERVICE = "Service";
        public static final String POSFIXO_REPOSITORY = "Repository";
        public static final String POSFIXO_REPOSITORY_IMPL = "RepositoryImpl";
    }

    static class Desafio {
        public static final String PACOTE_BASE = "com.caqqi.matematicaplay.operacao.desafio";
        public static final String PACOTE_API = "com.caqqi.matematicaplay.operacao.desafio.api";
        public static final String PACOTE_DOMAIN = "com.caqqi.matematicaplay.operacao.desafio.domain";
    }

}
