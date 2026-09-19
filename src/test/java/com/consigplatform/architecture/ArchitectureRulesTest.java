package com.consigplatform.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureRulesTest {

    private static JavaClasses applicationClasses;

    @BeforeAll
    static void importApplicationClasses() {
        applicationClasses = new ClassFileImporter()
                .importPackages("com.consigplatform");
    }

    @Test
    void domainMustNotDependOnInfrastructure() {
        ArchRule rule = noClasses()
                .that().resideInAnyPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..infrastructure..")
                .allowEmptyShould(true);

        rule.check(applicationClasses);
    }

    @Test
    void domainMustNotDependOnSpring() {
        ArchRule rule = noClasses()
                .that().resideInAnyPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("org.springframework..")
                .allowEmptyShould(true);

        rule.check(applicationClasses);
    }

    @Test
    void modulesMustNotDependOnAnotherModulesInternalPackages() {
        ArchRule rule = noClasses()
                .that().resideInAnyPackage("com.consigplatform.access..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("com.consigplatform.partners..", "com.consigplatform.credit..");

        rule.check(applicationClasses);
    }
}
