package tech.nightsky.budgetly.model;

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
     * Статус импорта (SUCCESS/ERROR/STARTED)
     */
    private ImportStatus status;
}
