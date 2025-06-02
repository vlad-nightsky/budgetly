package tech.nightsky.budgetly;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModularityTests {
    @Test
    void verifyModularity() {
        var modules = ApplicationModules.of(BudgetlyApplication.class);
        modules.forEach(System.out::println);

        var documenter = new Documenter(modules);

        documenter
                .writeModuleCanvases() // Показывать заголовки модулей
                .writeDocumentation();

        modules.verify();
    }
}
