package tech.nightsky.budgetly.category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface CategoryControllerMapper {
    CategoryResponse map(CategorySummary categorySummary);
}
