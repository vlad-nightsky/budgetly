package tech.nightsky.budgetly.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.doc.Docs;
import tech.nightsky.budgetly.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(title = Docs.TransactionResponse.TITLE, description = Docs.TransactionResponse.DESCRIPTION)
public record TransactionResponse(
        @Schema(
                title = Docs.TransactionResponse.Id.TITLE,
                description = Docs.TransactionResponse.Id.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.TransactionResponse.Id.EXAMPLE
        )
        Long id,

        @Schema(
                title = Docs.TransactionResponse.Amount.TITLE,
                description = Docs.TransactionResponse.Amount.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.TransactionResponse.Amount.EXAMPLE,
                format = Docs.TransactionResponse.Amount.FORMAT,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        BigDecimal amount,

        @Schema(
                title = Docs.TransactionResponse.Description.TITLE,
                description = Docs.TransactionResponse.Description.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.TransactionResponse.Description.EXAMPLE
        )
        String description,

        @Schema(
                title = Docs.TransactionResponse.Type.TITLE,
                description = Docs.TransactionResponse.Type.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.TransactionResponse.Type.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        TransactionType type,

        @Schema(
                title = Docs.TransactionResponse.Category.TITLE,
                description = Docs.TransactionResponse.Category.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        CategoryResponse category,

        @Schema(
                title = Docs.TransactionResponse.Account.TITLE,
                description = Docs.TransactionResponse.Account.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        AccountResponse account,

        @Schema(
                title = Docs.TransactionResponse.CreatedAt.TITLE,
                description = Docs.TransactionResponse.CreatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.TransactionResponse.CreatedAt.EXAMPLE
        )
        LocalDateTime createdAt,

        @Schema(
                title = Docs.TransactionResponse.UpdatedAt.TITLE,
                description = Docs.TransactionResponse.UpdatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.TransactionResponse.UpdatedAt.EXAMPLE
        )
        LocalDateTime updatedAt) {
}
