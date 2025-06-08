package tech.nightsky.budgetly.category.internal;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.MessageSource;
import tech.nightsky.budgetly.account.AccountSummary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Категория трат (например, "Еда", "Транспорт", "Развлечения").
 */
@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "category", schema = "budgyscheme")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Category {
    /**
     * Список кодов для категорий.
     * Содержит общие категории для системы.
     */
    public static final List<String> DEFAULT_CATEGORY_CODES = List.of(
            "FOOD",          // Рестораны, кафе, фастфуд
            "MARKET",        // Супермаркеты и магазины
            "HOME",          // Дом, бытовая химия
            "LEISURE",       // Кинотеатры, игры, путешествия
            "HEALTH",        // Аптеки и врачи
            "TRANSPORT",     // Транспорт и такси
            "SUBSCRIPTIONS", // Разные подписочные сервисы
            "UTILITIES",     // ЖКХ
            "TOURISM",        // Туризм, охота и рыбалка
            "GIFTS",         // Подарки
            "PARTNER",       // Партнёр
            "OTHER"          // То что не попало под другие категории
    );
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    /**
     * Дата создания
     */
    protected LocalDateTime createdAt;
    /**
     * Дата последнего обновления
     */
    protected LocalDateTime updatedAt;
    /**
     * Название категории.
     * <p>
     * Обязательное поле
     */
    private String name;
    /**
     * Код категории.
     * <p>
     * Уникальный в разрезе пользователя.
     * Позволяет системе понимать какая категория перед ним.
     */
    private String code;
    /**
     * Идентификатор аккаунта (связь Many-to-One).
     * <p>
     * Обязательное поле
     */
    @JoinColumn(name = "account_id", nullable = false)
    private Long accountId;

    @Builder(builderMethodName = "defaultCategoriesBuilder", builderClassName = "DefaultCategoriesBuilder")
    private static List<Category> categories(@NonNull MessageSource messageSource, @NonNull AccountSummary account, @NonNull Locale locale) {
        return DEFAULT_CATEGORY_CODES.stream()
                .map(code -> Category.builder()
                        .accountId(account.id())
                        .code(code)
                        .name(messageSource.getMessage((CategoryConst.MESSAGE_SOURCE_NAME + code).toLowerCase(locale), null, locale))
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
    }
}
