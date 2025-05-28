package tech.nightsky.budgetly.category.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.category.dto.CategoryResponse;
import tech.nightsky.budgetly.category.dto.CategorySummary;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategorySummary map(Category category);

    CategoryResponse map(CategorySummary categorySummary);
}
