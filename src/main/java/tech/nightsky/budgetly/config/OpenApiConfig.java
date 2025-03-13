package tech.nightsky.budgetly.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Budgetly API")
                        .version("0.0.1-SNAPSHOT")
                        .description("API для приложения управления финансами.")
                        .contact(new Contact()
                                .name("Владос Кирьянов")
                                .url("https://t.me/vnightsky")
                                .email("vlad.nightsky@gmail.com"))
                        .summary("Приложения управления личным бюджетом.")
                        .license(new License()
                                .url("https://spdx.org/licenses/AGPL-3.0-or-later.html")
                                .name("GNU Affero General Public License v3.0")
                                .identifier("AGPL-3.0-or-later")))
                .servers(Collections.singletonList(new Server()
                        .url("http://localhost:8080")
                        .description("Локальный сервер")))
                .components(
                        new Components()
                                .addHeaders("Location", new Header()
                                        .description("Ссылка на созданный ресурс")
                                        .example("http://localhost:8080/api/v1/resource/1")
                                        .schema(new StringSchema()))
                                .addParameters("PathId", new Parameter().name("ID")
                                        .description("Уникальный идентификатор ресурса. Например ID аккаунта или транзакции.")
                                        .schema(new Schema<Long>().type("integer").format("int64"))
                                        .example(1)
                                        .in(ParameterIn.PATH.toString())
                                        .allowEmptyValue(false)
                                        .required(true)
                                )
                );
    }
}
