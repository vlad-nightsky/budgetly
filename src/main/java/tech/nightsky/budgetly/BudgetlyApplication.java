package tech.nightsky.budgetly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;

@Modulith(systemName = "Budgetly")
@SpringBootApplication
public class BudgetlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetlyApplication.class, args);
    }

}
