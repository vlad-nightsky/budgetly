package tech.nightsky.budgetly;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTests {
    @Test
    void verifyModularity() {
        ApplicationModules.of(BudgetlyApplication.class).verify();
    }
}
