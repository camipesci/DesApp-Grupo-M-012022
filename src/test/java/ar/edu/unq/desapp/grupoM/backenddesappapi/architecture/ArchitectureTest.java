package ar.edu.unq.desapp.grupoM.backenddesappapi.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses (packages = "ar.edu.unq.desapp.grupoM.backenddesappapi")
public class ArchitectureTest {



    @ArchTest
    public static final ArchRule repositoryAnnotationShouldBeUsedOnlyInRepositoryClases = classes()
            .that().areAnnotatedWith(Repository.class)
            .should().resideInAPackage("..repository..");


    @ArchTest
    public static final ArchRule onlyServiceCanAccesAndCallRepository = classes()
            .that().resideInAPackage("..repository..")
            .should().onlyBeAccessed().byAnyPackage("..service..", "..repository..");


    @ArchTest
    public static final ArchRule controllersCanOnlyBeAccesedByController = classes()
        .that().resideInAPackage("..controller..")
        .should().onlyBeAccessed().byAnyPackage("..controller..");

    @ArchTest
    public static final ArchRule onlyClassesInControllerShouldHaveRestControllerAnnotation = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage("..controller..");

    @ArchTest
    public static final ArchRule servicesShouldOnlyBeAccessedByControllersAndAspects = classes()
        .that().resideInAPackage ("..service..")
        .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..", "..aspects..", "..security..");


    @ArchTest
    public static final ArchRule classesWithNameServiceAtTheEndShouldBeOnlyInServicePackage = classes()
            .that().haveSimpleNameEndingWith("Service")
            .should().resideInAPackage("..service..");

    @ArchTest
    public static final ArchRule classesWithNameControllerAtTheEndShouldBeOnlyInControllerPackage = classes()
            .that().haveSimpleNameEndingWith("Controller")
            .should().resideInAPackage("..controller..");

    @ArchTest
    public static final ArchRule onlyClassesInModelShouldHaveEntityAnnotation = classes()
            .that().areAnnotatedWith(Entity.class)
            .should().resideInAPackage("..model..");

    @ArchTest
    public static final ArchRule repositoryClassesShouldBeInterfaces = classes()
            .that().haveSimpleNameEndingWith("Repository")
            .should().beInterfaces();
}
