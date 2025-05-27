package tech.nightsky.budgetly.account;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Аккаунт пользователь системы
 */
@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "account", schema = "budgyscheme")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
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
     * Имя пользователя.
     * <p>
     * Нужно для входа.
     * Обязательное поле
     */
    private String username;

    /**
     * Хэшированный пароль пользователя.
     * <p>
     * Нужно для входа.
     * Обязательное поле
     */
    private String password;
}
