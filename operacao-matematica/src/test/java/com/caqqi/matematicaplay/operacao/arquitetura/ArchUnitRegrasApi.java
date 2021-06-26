package com.caqqi.matematicaplay.operacao.arquitetura;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Service;

@AnalyzeClasses(packages = ArchUnitUtil.Desafio.PACOTE_BASE)
public class ArchUnitRegrasApi {

    @ArchTest
    private static ArchRule na_camada_de_api_nao_pode_ter_classes_services =
        ArchRuleDefinition.classes()
            .that()
            .resideInAPackage(ArchUnitUtil.Desafio.PACOTE_API)
            .should()
            .notBeAnnotatedWith(Service.class)
            .andShould()
            .haveSimpleNameNotEndingWith(ArchUnitUtil.Core.POSFIXO_SERVICE);

    @ArchTest
    private static ArchRule na_camada_de_api_nao_pode_ter_classes_repository =
        ArchRuleDefinition.classes()
            .that()
            .resideInAPackage(ArchUnitUtil.Desafio.PACOTE_API)
            .should()
            .haveSimpleNameNotEndingWith(ArchUnitUtil.Core.POSFIXO_REPOSITORY)
            .orShould()
            .haveSimpleNameEndingWith(ArchUnitUtil.Core.POSFIXO_REPOSITORY_IMPL);

}
