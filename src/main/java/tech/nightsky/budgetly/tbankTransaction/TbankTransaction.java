package tech.nightsky.budgetly.tbankTransaction;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tech.nightsky.budgetly.tbankImport.TbankImport;

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
@EqualsAndHashCode(exclude = {"id","createdAt", "updatedAt", "tbankImport", "rowHash"})
public class TbankTransaction {
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
    @ManyToOne
    @JoinColumn(name = "import_id", nullable = false)
    private TbankImport tbankImport;

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
    private int rowHash;
}
