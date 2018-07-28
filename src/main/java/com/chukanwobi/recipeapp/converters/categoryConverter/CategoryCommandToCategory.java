package com.chukanwobi.recipeapp.converters.categoryConverter;

import com.chukanwobi.recipeapp.commands.CategoryCommand;
import com.chukanwobi.recipeapp.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand,Category> {

    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null){
            return null;
        }

        Category category =  new Category();
         category.setId(categoryCommand.getId());
         category.setDescription(categoryCommand.getDescription());


        return category;
    }
}
