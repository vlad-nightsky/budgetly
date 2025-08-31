package tech.nightsky.budgetly.tbankTransaction.internal;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tech.nightsky.budgetly.tbankTransaction.ParseResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Содержит сырые данные транзакций с привязкой к сессии импорта.
 */
@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "tbankTransaction", schema = "budgyscheme")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"id", "createdAt", "updatedAt", "importId", "rowHash", "parseResult"})
class TbankTransaction {
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
     * Идентификатор импорта
     */
    @JoinColumn(name = "import_id", nullable = false)
    private Long importId;

    /**
     * Дата и время операции
     */
    private LocalDateTime operationDate;

    /**
     * Дата платежа
     */
    private LocalDate paymentDate;

    /**
     * Номер карты (маскированный)
     */
    private String cardNumber;

    /**
     * Статус операции
     */
    private String status;

    /**
     * Сумма операции
     */
    private BigDecimal operationAmount;

    /**
     * Валюта операции
     */
    private String operationCurrency;

    /**
     * Сумма платежа
     */
    private BigDecimal paymentAmount;

    /**
     * Валюта платежа
     */
    private String paymentCurrency;

    /**
     * Размер кэшбэка
     */
    private BigDecimal cashback;

    /**
     * Категория операции
     */
    private String category;

    /**
     * Код MCC торговой точки
     */
    private Integer mcc;

    /**
     * Описание операции
     */
    private String description;

    /**
     * Бонусы (включая кэшбэк)
     */
    private BigDecimal bonuses;

    /**
     * Округление на инвесткопилку
     */
    private BigDecimal investmentRounding;

    /**
     * Сумма с округлением
     */
    private BigDecimal roundedOperationAmount;

    /**
     * Хеш всех полей
     */
    private Integer rowHash;

    /**
     * Результат парсинга транзакции
     * <p>
     * То есть результат перевода транзакции Т-Банк в системную транзакцию.
     */
    private ParseResult parseResult;

    public TbankTransaction finishParse(Long importId) {
        this.setImportId(importId);
        this.setRowHash(hashCode());
        this.setUpdatedAt(LocalDateTime.now());
        return this;
    }

    /**
     * Переводит транзакцию в статус отфильтрован
     */
    public void filtered() {
        this.updatedAt = LocalDateTime.now();
        this.parseResult = ParseResult.FILTERED;
    }

    /**
     * Переводит транзакцию в статус распарщен
     */
    public void parsed() {
        this.updatedAt = LocalDateTime.now();
        this.parseResult = ParseResult.PARSED;
    }
}
