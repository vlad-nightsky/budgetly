package tech.nightsky.budgetly.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.doc.Docs;
import tech.nightsky.budgetly.model.TransactionType;

import java.math.BigDecimal;

/**
 * Запрос на создание или обновление транзакции пользователя.
 *
 * @param amount      Сумма транзакции
 * @param description Описание транзакции
 * @param type        Тип транзакции
 * @param categoryId  Идентификатор категории транзакции
 * @param accountId   Идентификатор аккаунта пользователя
 */
@Schema(
        title = Docs.TransactionRequest.TITLE,
        description = Docs.TransactionRequest.DESCRIPTION
)
public record TransactionRequest(
        @Schema(
                title = Docs.TransactionRequest.Amount.TITLE,
                description = Docs.TransactionRequest.Amount.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.TransactionRequest.Amount.EXAMPLE,
                format = Docs.TransactionRequest.Amount.FORMAT,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        BigDecimal amount,

        @Schema(
                title = Docs.TransactionRequest.Description.TITLE,
                description = Docs.TransactionRequest.Description.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.TransactionRequest.Description.EXAMPLE
        )
        String description,

        @Schema(
                title = Docs.TransactionRequest.Type.TITLE,
                description = Docs.TransactionRequest.Type.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.TransactionRequest.Type.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        TransactionType type,

        @Schema(
                title = Docs.TransactionRequest.CategoryId.TITLE,
                description = Docs.TransactionRequest.CategoryId.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.TransactionRequest.CategoryId.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long categoryId,

        @Schema(
                title = Docs.TransactionRequest.AccountId.TITLE,
                description = Docs.TransactionRequest.AccountId.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.TransactionRequest.AccountId.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long accountId
) {
}