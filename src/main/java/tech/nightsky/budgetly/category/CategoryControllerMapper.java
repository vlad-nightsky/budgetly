package tech.nightsky.budgetly.category;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.category.dto.CategoryResponse;
import tech.nightsky.budgetly.category.dto.CategorySummary;

@Mapper(componentModel = "spring")
interface CategoryControllerMapper {
    CategoryResponse map(CategorySummary categorySummary);
}
