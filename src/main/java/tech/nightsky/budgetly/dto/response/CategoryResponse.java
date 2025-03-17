package tech.nightsky.budgetly.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.doc.Docs;

import java.time.LocalDateTime;

@Schema(
        title = Docs.CategoryResponse.TITLE,
        description = Docs.CategoryResponse.DESCRIPTION
)
public record CategoryResponse(
        @Schema(
                title = Docs.CategoryResponse.Id.TITLE,
                description = Docs.CategoryResponse.Id.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.CategoryResponse.Id.EXAMPLE
        )
        Long id,

        @Schema(
                title = Docs.CategoryResponse.Name.TITLE,
                description = Docs.CategoryResponse.Name.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.CategoryResponse.Name.EXAMPLE
        )
        String name,

        @Schema(
                title = Docs.CategoryResponse.Account.TITLE,
                description = Docs.CategoryResponse.Account.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        AccountResponse account,

        @Schema(
                title = Docs.CategoryResponse.CreatedAt.TITLE,
                description = Docs.CategoryResponse.CreatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.CategoryResponse.CreatedAt.EXAMPLE
        )
        LocalDateTime createdAt,

        @Schema(
                title = Docs.CategoryResponse.UpdatedAt.TITLE,
                description = Docs.CategoryResponse.UpdatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.CategoryResponse.UpdatedAt.EXAMPLE
        )
        LocalDateTime updatedAt
) {
}
