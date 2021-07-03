package com.caqqi.matematicaplay.desafios.arquitetura;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = ArchUnitUtil.Desafio.PACOTE_BASE)
public class ArchUnitRegrasService {

    @ArchTest
    private static ArchRule na_camada_de_service_nao_pode_ter_classes_controller =
        ArchRuleDefinition.classes()
            .that()
            .resideInAPackage(ArchUnitUtil.Desafio.PACOTE_DOMAIN)
            .should()
            .notBeAnnotatedWith(RestController.class)
            .andShould()
            .haveSimpleNameNotEndingWith(ArchUnitUtil.Core.POSFIXO_SERVICE);

}
