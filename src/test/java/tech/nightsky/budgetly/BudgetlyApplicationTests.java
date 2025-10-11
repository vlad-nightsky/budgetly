package tech.nightsky.budgetly;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

//todo нужно больше тестов
//todo в gradle не запускаются тесты из за базы
//todo загрузка контекста
@SpringBootTest
class BudgetlyApplicationTests {

    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("budgetly-test")
            .withUsername("budgetly-test-USER")
            .withPassword("budgetly");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void startContainer() {
        postgres.start(); // нужно явно
    }

    @Test
    void contextLoads() {
    }
}
