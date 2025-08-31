package tech.nightsky.budgetly.tbankImport.internal;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tech.nightsky.budgetly.tbankImport.ImportStatus;

import java.time.LocalDateTime;

/**
 * Хранит метаинформацию о сессии импорта данных из Т-Банка.
 */
@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "tbankImport", schema = "budgyscheme")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class TbankImport {
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
     * Количество транзакций
     */
    private Integer transactionCount;

    //TODO Можно статистику сделать картой status: count
    /**
     * Количество транзакций сохранённых в базу
     */
    private Long saved;

    /**
     * Количество пропущенных транзакций.
     * Транзакции не сохранены потому что дубликаты
     */
    private Long skipped;

    /**
     * Количество транзакций успешно обработанных в системную транзакцию.
     */
    private Long parsed;

    /**
     * Количество транзакций которые отфильтрованные в связи с правилами фильтрации.
     */
    private Long filtered;

    /**
     * Статус импорта (SUCCESS/ERROR/STARTED)
     */
    private ImportStatus status;

    /**
     * Создаёт пустой объект информации об импорте со стандартной информацией
     *
     * @return объект с метаинформацией о сессии импорта данных из Т-Банка
     */
    public static TbankImport init() {
        return TbankImport.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .transactionCount(0)
                .skipped(0L)
                .saved(0L)
                .filtered(0L)
                .parsed(0L)
                .status(ImportStatus.STARTED)
                .build();
    }

    //todo параметры long skipped, long saved, long parsed, long filtered можно сгруппировать в Record
    public TbankImport setSuccess(Integer transactionCount, long skipped, long saved, long parsed, long filtered) {
        if (transactionCount == null || transactionCount <= 0) {
            throw new IllegalArgumentException("TransactionCount must be greater than 0");
        }
        if (skipped < 0) {
            throw new IllegalArgumentException("Skipped must be greater than 0");
        }
        if (saved < 0) {
            throw new IllegalArgumentException("Saved must be greater than 0");
        }
        if (parsed < 0) {
            throw new IllegalArgumentException("Parsed must be greater than 0");
        }
        if (filtered < 0) {
            throw new IllegalArgumentException("Filtered must be greater than 0");
        }
        this.setUpdatedAt(LocalDateTime.now());
        this.setTransactionCount(transactionCount);
        this.setSaved(saved);
        this.setSkipped(skipped);
        this.setParsed(parsed);
        this.setFiltered(filtered);
        this.setStatus(ImportStatus.SUCCESS);
        return this;
    }

    public TbankImport setError() {
        this.setUpdatedAt(LocalDateTime.now());
        this.setStatus(ImportStatus.ERROR);
        return this;
    }
}
