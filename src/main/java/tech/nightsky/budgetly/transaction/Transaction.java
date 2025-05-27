package tech.nightsky.budgetly.transaction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tech.nightsky.budgetly.account.Account;
import tech.nightsky.budgetly.category.Category;
import tech.nightsky.budgetly.tbankTransaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "transaction", schema = "budgyscheme")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {
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
     * Сумма транзакции.
     * <p>
     * Обязательное поле
     */
    private BigDecimal amount;

    /**
     * Описание транзакции.
     * <p>
     * Обязательное поле
     */
    private String description;

    /**
     * Тип транзакции (доход или расход).
     * <p>
     * Обязательное поле
     */
    private TransactionType type;

    /**
     * Категория трат (связь Many-to-One).
     * <p>
     * Обязательное поле
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * Идентификатор пользователя (связь Many-to-One).
     * <p>
     * Обязательное поле
     */
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}