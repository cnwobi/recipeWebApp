package com.chukanwobi.recipeapp.converters.categoryConverter;

import com.chukanwobi.recipeapp.commands.CategoryCommand;
import com.chukanwobi.recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component

public class CategoryToCategoryCommand implements Converter<Category,CategoryCommand> {

    @Override
    @Synchronized
    @Nullable
    public CategoryCommand convert(Category category) {
        if(category ==null){
            return null;
        }
       CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());
        categoryCommand.setRecipes(category.getRecipes());
        return categoryCommand;
    }
}
