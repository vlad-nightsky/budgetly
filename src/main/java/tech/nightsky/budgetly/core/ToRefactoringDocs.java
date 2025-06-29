package tech.nightsky.budgetly.core;

/**
 * Класс для хранения документации
 */
public class ToRefactoringDocs {

    public static class AccountController {
        public static final String TITLE = "Управление аккаунтами";
        public static final String DESCRIPTION = "Операции по управлению аккаунтами пользователя.";

        public static class GetAll {
            public static final String SUMMARY = "Список аккаунтов пользователей";
            public static final String DESCRIPTION = "Возвращает список аккаунтов пользователей.";
            public static final String MESSAGE = "Получен список аккаунтов пользователей.";
        }

        public static class GetById {
            public static final String SUMMARY = "Информация об аккаунте";
            public static final String DESCRIPTION = "Запрос на получение информации об аккаунте пользователя.";
            public static final String SUCCESS_MESSAGE = "Аккаунт найден.";
            public static final String NOT_FOUND_MESSAGE = "Аккаунт не найден.";
        }

        public static class Save {
            public static final String SUMMARY = "Создание аккаунта";
            public static final String DESCRIPTION = "Запрос на создание аккаунта пользователя. В случае успеха вернёт данные по аккаунту.";
            public static final String SUCCESS_MESSAGE = "Аккаунт успешно создан.";
        }

        public static class Update {
            public static final String SUMMARY = "Обновление аккаунта";
            public static final String DESCRIPTION = "Запрос на обновление аккаунта пользователя. В случае успеха вернёт данные по аккаунту.";
            public static final String SUCCESS_MESSAGE = "Аккаунт успешно обновлён.";
        }

        public static class Delete {
            public static final String SUMMARY = "Удаление аккаунта";
            public static final String DESCRIPTION = "Запрос на удаление аккаунта пользователя. Аккаунт удаляется безвозвратно, восстановить его нельзя.";
            public static final String SUCCESS_MESSAGE = "Аккаунт успешно удалён. Нечего возвращать.";
        }
    }

    public static class TransactionController {
        public static final String NAME = "Управление транзакциями";
        public static final String DESCRIPTION = "Операции по управлению транзакциями пользователя.";

        public static class GetAll {
            public static final String SUMMARY = "Список транзакций";
            public static final String DESCRIPTION = "Возвращает список транзакций пользователя.";
            public static final String MESSAGE = "Получен список транзакций.";
        }

        public static class GetById {
            public static final String SUMMARY = "Информация о транзакции";
            public static final String DESCRIPTION = "Запрос на получение информации о транзакции пользователя.";
            public static final String SUCCESS_MESSAGE = "Транзакция найдена.";
            public static final String NOT_FOUND_MESSAGE = "Транзакция не найдена.";
        }

        public static class Save {
            public static final String SUMMARY = "Создание транзакции";
            public static final String DESCRIPTION = "Запрос на создание транзакции. В случае успеха вернёт данные по транзакции.";
            public static final String SUCCESS_MESSAGE = "Транзакция успешно создана.";
        }

        public static class Update {
            public static final String SUMMARY = "Обновление транзакции";
            public static final String DESCRIPTION = "Запрос на обновление транзакции. В случае успеха вернёт данные по транзакции.";
            public static final String SUCCESS_MESSAGE = "Транзакция успешно обновлена.";
        }

        public static class Delete {
            public static final String SUMMARY = "Удаление транзакции";
            public static final String DESCRIPTION = "Запрос на удаление транзакции. Транзакция удаляется безвозвратно, восстановить её нельзя.";
            public static final String SUCCESS_MESSAGE = "Транзакция успешно удалена. Нечего возвращать.";
        }
    }

    public static class CategoryController {
        public static final String NAME = "Управление категориями";
        public static final String DESCRIPTION = "Операции по управлению категориями транзакций пользователей.";

        public static class GetAll {
            public static final String SUMMARY = "Список категорий";
            public static final String DESCRIPTION = "Возвращает список категорий транзакций пользователей.";
            public static final String MESSAGE = "Получен список категорий транзакций.";
        }

        public static class GetById {
            public static final String SUMMARY = "Информация о категории";
            public static final String DESCRIPTION = "Запрос на получение информации о категории транзакций.";
            public static final String SUCCESS_MESSAGE = "Категория найдена.";
            public static final String NOT_FOUND_MESSAGE = "Категория не найдена.";
        }

        public static class Save {
            public static final String SUMMARY = "Создание категории";
            public static final String DESCRIPTION = "Запрос на создание категории транзакций. В случае успеха вернёт данные по категории.";
            public static final String SUCCESS_MESSAGE = "Категория успешно создана.";
        }

        public static class Update {
            public static final String SUMMARY = "Обновление категории";
            public static final String DESCRIPTION = "Запрос на обновление категории транзакций. В случае успеха вернёт данные по категории.";
            public static final String SUCCESS_MESSAGE = "Категория успешно обновлена.";
        }

        public static class Delete {
            public static final String SUMMARY = "Удаление категории";
            public static final String DESCRIPTION = "Запрос на удаление категории транзакций. Категория удаляется безвозвратно, восстановить её нельзя.";
            public static final String SUCCESS_MESSAGE = "Категория успешно удалена. Нечего возвращать.";
        }
    }

    public static class ImportController {
        public static final String TITLE = "Импорт транзакций";
        public static final String DESCRIPTION = "Операции с импортом транзакций из разных банков.";

        public static class ImportCsv {
            public static final String SUMMARY = "Из файла CSV";
            public static final String DESCRIPTION = "Импорт транзакций банка из файла с типом CSV.";
            public static final String MESSAGE = "Список успешно импортирован.";
        }
    }

    public static final class AccountRequest {
        public static final String TITLE = "Создать/обновить аккаунт";
        public static final String DESCRIPTION = "Запрос на создание или обновление пользователя.";

        public static final class Username {
            public static final String TITLE = "Имя пользователя";
            public static final String DESCRIPTION = "Имя пользователя. Используется для авторизации.";
            public static final String EXAMPLE = "catra";
        }

        public static final class Password {
            public static final String TITLE = "Пароль пользователя";
            public static final String DESCRIPTION = "Пароль пользователя. Используется для авторизации.";
            public static final String EXAMPLE = "adora";
        }
    }

    public static final class CategoryRequest {
        public static final String TITLE = "Создать/обновить категорию";
        public static final String DESCRIPTION = "Запрос на создание или обновление категории трат пользователя.";

        public static final class Name {
            public static final String TITLE = "Название категории трат";
            public static final String DESCRIPTION = "Название категории трат нужно для идентификации категории для пользователя.";
            public static final String EXAMPLE = "Продукты";
        }

        public static final class AccountId {
            public static final String TITLE = "Идентификатор аккаунта пользователя";
            public static final String DESCRIPTION = "Идентификатор аккаунта пользователя требуется для того, чтобы связать конкретную категорию трат с конкретным пользователем.";
            public static final String EXAMPLE = "1";
        }
    }

    public static final class TransactionRequest {
        public static final String TITLE = "Создать/обновить транзакцию";
        public static final String DESCRIPTION = "Запрос на создание или обновление транзакции пользователя.";

        public static final class Amount {
            public static final String TITLE = "Сумма";
            public static final String DESCRIPTION = "Сумма транзакции. Может быть только положительной или нулём.";
            public static final String EXAMPLE = "100.50";
            public static final String FORMAT = "decimal";
        }

        public static final class Description {
            public static final String TITLE = "Описание";
            public static final String DESCRIPTION = "Текстовое описание транзакции, которое помогает идентифицировать цель транзакции.";
            public static final String EXAMPLE = "Покупка продуктов";
        }

        public static final class Type {
            public static final String TITLE = "Тип транзакции";
            public static final String DESCRIPTION = "Тип транзакции: доход (INCOME) или расход (EXPENSE).";
            public static final String EXAMPLE = "EXPENSE";
        }

        public static final class CategoryId {
            public static final String TITLE = "Идентификатор категории трат";
            public static final String DESCRIPTION = "Идентификатор категории трат пользователя, к которой относится транзакция. Например, \"Еда\", \"Транспорт\" и т.д.";
            public static final String EXAMPLE = "1";
        }

        public static final class AccountId {
            public static final String TITLE = "Идентификатор аккаунта пользователя";
            public static final String DESCRIPTION = "Идентификатор аккаунта пользователя, с которым связана транзакция.";
            public static final String EXAMPLE = "1";
        }
    }

    public static final class AccountResponse {
        public static final String TITLE = "Информация об аккаунте";
        public static final String DESCRIPTION = "Содержит информацию об аккаунте пользователя.";

        public static final class Id {
            public static final String TITLE = "Идентификатор";
            public static final String DESCRIPTION = "Указатель на аккаунт пользователя. Генерируется на стороне сервера.";
            public static final String EXAMPLE = "1";
        }

        public static final class Username {
            public static final String TITLE = "Имя пользователя";
            public static final String DESCRIPTION = "Имя пользователя. Используется для авторизации.";
            public static final String EXAMPLE = "catra";
        }

        public static final class CreatedAt {
            public static final String TITLE = "Дата создания";
            public static final String DESCRIPTION = "Дата и время создания объекта.";
            public static final String EXAMPLE = "2025-03-01T08:40:34.081287";
        }

        public static final class UpdatedAt {
            public static final String TITLE = "Дата обновления";
            public static final String DESCRIPTION = "Дата и время обновления объекта.";
            public static final String EXAMPLE = "2025-03-01T08:40:34.081287";
        }
    }

    public static final class CategoryResponse {
        public static final String TITLE = "Информация о категории";
        public static final String DESCRIPTION = "Содержит информацию о категории трат пользователя.";

        public static final class Id {
            public static final String TITLE = "Идентификатор категории трат";
            public static final String DESCRIPTION = "Идентификатор категории трат нужен для идентификации категории.";
            public static final String EXAMPLE = "1";
        }

        public static final class Name {
            public static final String TITLE = "Название категории трат";
            public static final String DESCRIPTION = "Название категории трат нужно для идентификации категории для пользователя.";
            public static final String EXAMPLE = "Продукты";
        }

        public static final class Code {
            public static final String TITLE = "Код категории";
            public static final String DESCRIPTION = "Код категории помогает системе определять категорию при парсинге. Должно быть уникальным.";
            public static final String EXAMPLE = "MARKET";
        }

        public static final class Account {
            public static final String TITLE = "Информация об аккаунте пользователя";
            public static final String DESCRIPTION = "Информация об аккаунте пользователя.";
        }

        public static final class CreatedAt {
            public static final String TITLE = "Дата создания";
            public static final String DESCRIPTION = "Дата и время создания объекта.";
            public static final String EXAMPLE = "2025-03-01T08:40:34.081287";
        }

        public static final class UpdatedAt {
            public static final String TITLE = "Дата обновления";
            public static final String DESCRIPTION = "Дата и время обновления объекта.";
            public static final String EXAMPLE = "2025-03-01T08:40:34.081287";
        }
    }

    public static final class ErrorDetailResponse {
        public static final String TITLE = "Детализированная информация об ошибке";
        public static final String DESCRIPTION = "Представляет детализированную информацию об ошибке. Используется для указания конкретных полей и сообщений об ошибках.";

        public static final class Field {
            public static final String TITLE = "Название поля";
            public static final String DESCRIPTION = "Название поля, связанного с ошибкой (например, \"email\", \"password\").";
            public static final String EXAMPLE = "email";
        }

        public static final class Message {
            public static final String TITLE = "Сообщение об ошибке";
            public static final String DESCRIPTION = "Сообщение об ошибке для конкретного поля. Сообщение поможет понять, как правильно вводить данные.";
            public static final String EXAMPLE = "Email must be a valid format";
        }
    }

    public static final class ErrorResponse {
        public static final String ERROR_TITLE = "Тип ошибки";
        public static final String ERROR_DESCRIPTION = "Общий тип ошибки (например, \"ValidationError\", \"InternalServerError\", \"NotFound\").";
        public static final String ERROR_EXAMPLE = "ValidationError";

        public static final String MESSAGE_TITLE = "Сообщение об ошибке";
        public static final String MESSAGE_DESCRIPTION = "Сообщение об ошибке, предназначенное для пользователя или разработчика.";
        public static final String MESSAGE_EXAMPLE = "Invalid input data";

        public static final String CODE_TITLE = "Сообщение об ошибке";
        public static final String CODE_DESCRIPTION = "HTTP-статус ошибки";
        public static final String CODE_EXAMPLE = "400";

        public static final String DETAILS_TITLE = "Детали ошибки";
        public static final String DETAILS_DESCRIPTION = "Список содержащий детализированную информацию об ошибке";
    }

    public static final class TransactionResponse {
        public static final String TITLE = "Информация о транзакции";
        public static final String DESCRIPTION = "Содержит информацию об транзакции пользователя.";

        public static final class Id {
            public static final String TITLE = "Идентификатор транзакции";
            public static final String DESCRIPTION = "Идентификатор транзакции пользователя.";
            public static final String EXAMPLE = "1";
        }

        public static final class Amount {
            public static final String TITLE = "Сумма";
            public static final String DESCRIPTION = "Сумма транзакции. Может быть только положительной или нулём.";
            public static final String EXAMPLE = "100.50";
            public static final String FORMAT = "decimal";
        }

        public static final class Description {
            public static final String TITLE = "Описание";
            public static final String DESCRIPTION = "Текстовое описание транзакции, которое помогает идентифицировать цель транзакции.";
            public static final String EXAMPLE = "Покупка продуктов";
        }

        public static final class Type {
            public static final String TITLE = "Тип транзакции";
            public static final String DESCRIPTION = "Тип транзакции: доход (INCOME) или расход (EXPENSE).";
            public static final String EXAMPLE = "EXPENSE";
        }

        public static final class Category {
            public static final String TITLE = "Информация о категории тарт пользователя";
            public static final String DESCRIPTION = "Информация о категории тарт пользователя, к которой относится транзакция. Например, \"Еда\", \"Транспорт\" и т.д.";
        }

        public static final class Account {
            public static final String TITLE = "Информация об аккаунте пользователя";
            public static final String DESCRIPTION = "Информация об аккаунте пользователя, к которму относится транзакция.";
        }

        public static final class CreatedAt {
            public static final String TITLE = "Дата создания";
            public static final String DESCRIPTION = "Дата и время создания объекта.";
            public static final String EXAMPLE = "2025-03-01T08:40:34.081287";
        }

        public static final class UpdatedAt {
            public static final String TITLE = "Идентификатор";
            public static final String DESCRIPTION = "Дата и время обновления объекта.";
            public static final String EXAMPLE = "2025-03-01T08:40:34.081287";
        }
    }
}