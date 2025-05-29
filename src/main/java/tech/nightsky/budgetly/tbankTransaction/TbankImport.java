package tech.nightsky.budgetly.tbankTransaction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
public class TbankImport {
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

    /**
     * Количество транзакций сохранённых в базу
     */
    private Integer saved;

    /**
     * Количество пропущенных транзакций.
     * Транзакции не сохранены потому что дубликаты
     */
    private Integer skipped;

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
                .skipped(0)
                .saved(0)
                .status(ImportStatus.STARTED)
                .build();
    }

    public TbankImport setSuccess(Integer transactionCount, int skipped, int saved) {
        if (transactionCount == null || transactionCount <= 0) {
            throw new IllegalArgumentException("TransactionCount must be greater than 0");
        }
        if (skipped < 0) {
            throw new IllegalArgumentException("Skipped must be greater than 0");
        }
        if (saved < 0) {
            throw new IllegalArgumentException("Saved must be greater than 0");
        }
        this.setUpdatedAt(LocalDateTime.now());
        this.setTransactionCount(transactionCount);
        this.setSaved(saved);
        this.setSkipped(skipped);
        this.setStatus(ImportStatus.SUCCESS);
        return this;
    }

    public TbankImport setError() {
        this.setUpdatedAt(LocalDateTime.now());
        this.setStatus(ImportStatus.ERROR);
        return this;
    }
}
