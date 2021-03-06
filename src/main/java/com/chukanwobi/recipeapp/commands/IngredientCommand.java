package com.chukanwobi.recipeapp.commands;

import com.chukanwobi.recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@Getter
@Setter
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;
    private Long recipeId;
private  Long uomId;

    @Override
    public String toString() {
        return "IngredientCommand{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", unitOfMeasure=" + unitOfMeasure +
                ", recipeId=" + recipeId +
                ", uomId=" + uomId +
                '}';
    }
}
