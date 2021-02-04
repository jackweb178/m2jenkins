package gn.balde.devoir;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("gn.balde.devoir");

        noClasses()
            .that()
            .resideInAnyPackage("gn.balde.devoir.service..")
            .or()
            .resideInAnyPackage("gn.balde.devoir.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..gn.balde.devoir.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
