package tech.nightsky.budgetly.category.api;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.category.CategorySummary;

@Mapper(componentModel = "spring")
interface CategoryControllerMapper {
    CategoryResponse map(CategorySummary categorySummary);
}
