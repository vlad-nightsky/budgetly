package tech.nightsky.budgetly.category.internal;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tech.nightsky.budgetly.account.internal.Account;

import java.time.LocalDateTime;

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
     * Идентификатор аккаунта (связь Many-to-One).
     * <p>
     * Обязательное поле
     */
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    //todo нужно развязатся если хотим использовать модулит
    private Account account;
}
