package tech.nightsky.budgetly.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.doc.Docs;

import java.time.LocalDateTime;

@Schema(
        title = Docs.AccountResponse.TITLE,
        description = Docs.AccountResponse.DESCRIPTION
)
public record AccountResponse(
        @Schema(
                title = Docs.AccountResponse.Id.TITLE,
                description = Docs.AccountResponse.Id.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.AccountResponse.Id.EXAMPLE
        )
        Long id,

        @Schema(
                title = Docs.AccountResponse.Username.TITLE,
                description = Docs.AccountResponse.Username.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.AccountResponse.Username.EXAMPLE
        )
        String username,

        @Schema(
                title = Docs.AccountResponse.CreatedAt.TITLE,
                description = Docs.AccountResponse.CreatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.AccountResponse.CreatedAt.EXAMPLE
        )
        LocalDateTime createdAt,

        @Schema(
                title = Docs.AccountResponse.UpdatedAt.TITLE,
                description = Docs.AccountResponse.UpdatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.AccountResponse.UpdatedAt.EXAMPLE
        )
        LocalDateTime updatedAt
) {
}