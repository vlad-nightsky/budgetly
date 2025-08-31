package tech.nightsky.budgetly.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

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
        title = ToRefactoringDocs.TransactionRequest.TITLE,
        description = ToRefactoringDocs.TransactionRequest.DESCRIPTION
)
@Builder
public record TransactionRequest(
        @Schema(
                title = ToRefactoringDocs.TransactionRequest.Amount.TITLE,
                description = ToRefactoringDocs.TransactionRequest.Amount.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.TransactionRequest.Amount.EXAMPLE,
                format = ToRefactoringDocs.TransactionRequest.Amount.FORMAT,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        BigDecimal amount,

        @Schema(
                title = ToRefactoringDocs.TransactionRequest.Description.TITLE,
                description = ToRefactoringDocs.TransactionRequest.Description.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.TransactionRequest.Description.EXAMPLE
        )
        String description,

        @Schema(
                title = ToRefactoringDocs.TransactionRequest.Type.TITLE,
                description = ToRefactoringDocs.TransactionRequest.Type.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.TransactionRequest.Type.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        TransactionType type,

        @Schema(
                title = ToRefactoringDocs.TransactionRequest.CategoryId.TITLE,
                description = ToRefactoringDocs.TransactionRequest.CategoryId.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.TransactionRequest.CategoryId.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long categoryId,

        @Schema(
                title = ToRefactoringDocs.TransactionRequest.AccountId.TITLE,
                description = ToRefactoringDocs.TransactionRequest.AccountId.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.TransactionRequest.AccountId.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long accountId
) {
}