package com.consigplatform.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ArchitectureRulesTest {

    private static JavaClasses applicationClasses;

    @BeforeAll
    static void importApplicationClasses() {
        applicationClasses = new ClassFileImporter()
                .importPackages("com.consigplatform");
    }

    @Test
    void domainMustNotDependOnInfrastructure() {
        ArchRule rule = classes()
                .that().resideInAnyPackage("..domain..")
                .should().notDependOnClassesThat()
                .resideInAnyPackage("..infrastructure..");

        rule.check(applicationClasses);
    }

    @Test
    void domainMustNotDependOnSpring() {
        ArchRule rule = classes()
                .that().resideInAnyPackage("..domain..")
                .should().notDependOnClassesThat()
                .resideInAnyPackage("org.springframework..");

        rule.check(applicationClasses);
    }

    @Test
    void modulesMustNotDependOnAnotherModulesInternalPackages() {
        ArchRule rule = classes()
                .that().resideInAnyPackage("com.consigplatform.access..")
                .should().notDependOnClassesThat()
                .resideInAnyPackage("com.consigplatform.partners..", "com.consigplatform.credit..");

        rule.check(applicationClasses);
    }
}
