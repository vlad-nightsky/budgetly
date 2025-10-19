#!/bin/bash
set -e  # останавливаемся при ошибках

echo "☕ Проверяем Java"
java -version

echo "⚙️ Чистим проект и запускаем тесты"
./gradlew clean test

echo "🧹 Чистим папку с docs"
rm -rf docs/javadoc
rm -rf docs/problems
rm -rf docs/spring-modulith-docs
rm -rf docs/test
rm -rf docs/swagger/openapi.yaml

echo "⚙️ Генерируем Swagger OpenAPI документацию"
./gradlew generateOpenApiDocs

echo "⚙️ Генерируем JavaDoc"
./gradlew javadoc

echo "⚙️ Преобразуем документацию Modulith в HTML"
./gradlew convertModulithDocs

echo "🗄️ Копируем документацию в папку docs"
# JavaDoc
mkdir -p docs/javadoc
cp -r build/docs/javadoc/* docs/javadoc/

# Gradle Test & Problems
mkdir -p docs/problems
mkdir -p docs/test
cp -r build/reports/problems/problems-report.html docs/problems/index.html
cp -r build/reports/tests/test/* docs/test/

echo "✅ Документация собрана в ./docs"
