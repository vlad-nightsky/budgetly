package tech.nightsky.budgetly.category.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.category.CategorySummary;

@Mapper(componentModel = "spring")
interface CategoryMapper {
    CategorySummary toSummary(Category category);
}
