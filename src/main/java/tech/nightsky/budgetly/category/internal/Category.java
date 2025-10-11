package tech.nightsky.budgetly.category.internal;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tech.nightsky.budgetly.account.AccountSummary;
import tech.nightsky.budgetly.category.DefaultCategoryCodes;

import java.time.LocalDateTime;
import java.util.List;
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
public class Category {
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

    /**
     * Создаёт список категорий по умолчанию для указанного аккаунта.
     *
     * @param account объект AccountSummary, содержащий информацию об аккаунте пользователя
     * @return список стандартных категорий, ассоциированных с указанным аккаунтом
     */
    public static List<Category> categories(@NonNull AccountSummary account) {
        return DefaultCategoryCodes.getAllNames().stream()
                .map(code -> Category.builder()
                        .accountId(account.id())
                        .code(code)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
    }
}
