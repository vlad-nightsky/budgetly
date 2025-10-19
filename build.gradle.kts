plugins {
    java
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
    id("org.asciidoctor.jvm.convert") version "4.0.2"
}

group = "tech.nightsky"
version = "0.1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

openApi {
    outputFileName = "openapi.yaml"
    outputDir = file("$rootDir/docs/swagger")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.register<org.asciidoctor.gradle.jvm.AsciidoctorTask>("convertModulithDocs") {
    description = "Конвертирует Spring Modulith adoc в HTML"
    group = "documentation"

    // где лежат твои AsciiDoc файлы
    setSourceDir(file("build/spring-modulith-docs"))

    // куда класть HTML
    setOutputDir( file("docs/spring-modulith-docs"))

    // настройки Asciidoctor
    baseDirFollowsSourceFile()
    options(
        mapOf(
            "doctype" to "book",
            "backend" to "html5"
        )
    )
    attributes(
        mapOf(
            "toc" to "left",
            "icons" to "font",
            "sectanchors" to true,
            "source-highlighter" to "rouge",
            "encoding" to "utf-8"
        )
    )
    asciidoctorj{
        modules {
            diagram.use()
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework.modulith:spring-modulith-starter-core")
    implementation("org.springframework.modulith:spring-modulith-starter-jpa")
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.springframework.modulith:spring-modulith-actuator")
    runtimeOnly("org.springframework.modulith:spring-modulith-observability")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //todo выделить версии переменных в бом файл
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
    implementation("org.apache.commons:commons-csv:1.14.0")
    testImplementation("org.testcontainers:postgresql:1.20.0")
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.modulith:spring-modulith-bom:1.3.5")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
