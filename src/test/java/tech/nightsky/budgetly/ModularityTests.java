package tech.nightsky.budgetly;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModularityTests {

    static ApplicationModules modules =  ApplicationModules.of(BudgetlyApplication.class);

    @Test
    void verifyModularStructure() {
        modules.verify();
    }

    @Test
    void createModulesDocumentation() {
        new Documenter(modules).writeDocumentation();
    }
}
