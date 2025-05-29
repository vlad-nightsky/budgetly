package tech.nightsky.budgetly.category.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.category.CategoryResponse;
import tech.nightsky.budgetly.category.CategorySummary;

@Mapper(componentModel = "spring")
interface CategoryMapper {
    CategorySummary map(Category category);

    CategoryResponse map(CategorySummary categorySummary);
}
