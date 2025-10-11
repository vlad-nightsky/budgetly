package tech.nightsky.budgetly.category;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Перечисление кодов категорий.
 */
public enum DefaultCategoryCodes {
    FOOD,          // Рестораны, кафе, фастфуд
    MARKET,        // Супермаркеты и магазины
    HOME,          // Дом, бытовая химия
    FUN,        // Кинотеатры, игры, путешествия
    HEALTH,        // Аптеки и врачи
    TRANSPORT,     // Транспорт и такси
    SUBSCRIPTIONS, // Разные подписочные сервисы
    UTILITIES,     // ЖКХ
    TOURISM,       // Туризм, охота и рыбалка
    GIFTS,         // Подарки
    PARTNER,       // Партнёр
    SALARY,        // Зарплата
    DEPOSITS,      // Проценты по вкладам
    INVESTMENTS,   // Инвестиции на бирже
    CASHBACK,      // Кэшбек от трат
    OTHER;         // То что не попало под другие категории

    public static List<String> getAllNames() {
        return Arrays.stream(DefaultCategoryCodes.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
