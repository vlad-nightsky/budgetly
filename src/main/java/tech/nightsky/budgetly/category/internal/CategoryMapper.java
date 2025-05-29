package tech.nightsky.budgetly.category.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.category.CategorySummary;
import tech.nightsky.budgetly.category.api.CategoryResponse;

@Mapper(componentModel = "spring")
interface CategoryMapper {
    CategorySummary map(Category category);

    CategoryResponse map(CategorySummary categorySummary);
}
