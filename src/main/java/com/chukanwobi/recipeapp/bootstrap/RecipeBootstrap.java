package com.chukanwobi.recipeapp.bootstrap;

import com.chukanwobi.recipeapp.domain.*;
import com.chukanwobi.recipeapp.repositories.CategoryRepository;
import com.chukanwobi.recipeapp.repositories.IngredientsRepository;
import com.chukanwobi.recipeapp.repositories.RecipeRepository;
import com.chukanwobi.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private IngredientsRepository ingredientsRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recipeRepository.saveAll(getRecipes());
        log.debug("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nLoading bootstrap data\n\n\n\n\n\n\n\n\n\n\n");
    }

    public RecipeBootstrap(CategoryRepository categoryRepository, IngredientsRepository ingredientsRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes() {


        List<Recipe> recipes = new ArrayList<>();
        //get uoms
        Optional<UnitOfMeasure> eachUOMOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUOMOptional.isPresent()){
            throw  new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> teaSpoonUOMOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUOMOptional.isPresent()){
            throw new RuntimeException("Expected UOM no found");
        }

        Optional<UnitOfMeasure> tableSpoonUOMOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUOMOptional.isPresent()){
            throw new RuntimeException("Expected UOM no found");
        }
        Optional<UnitOfMeasure> cupsUOMOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupsUOMOptional.isPresent()){
            throw new RuntimeException("Expected  UOM no found");
        }

        Optional<UnitOfMeasure> dashUOMOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUOMOptional.isPresent()){
            throw new RuntimeException("Expected UOM no found");
        }
        Optional<UnitOfMeasure> pintUOMOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUOMOptional.isPresent()){
            throw new RuntimeException("Expected UOM no founds");
        }

        //get optionals

        UnitOfMeasure eachUom = eachUOMOptional.get();
        UnitOfMeasure tableSpoon = tableSpoonUOMOptional.get();
        UnitOfMeasure teaspoon = teaSpoonUOMOptional.get();
        UnitOfMeasure dash = dashUOMOptional.get();
        UnitOfMeasure pint = pintUOMOptional.get();
        UnitOfMeasure cups = cupsUOMOptional.get();


        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected category 'American' not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category 'Mexican' not found");
        }
        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);

        guacRecipe.addDirections(new Direction("Cut avocado, remove flesh: Cut the avocados in half. Remove seed. " +
                "Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon"));
        guacRecipe.addDirections(new Direction("Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"));
        guacRecipe.addDirections(new Direction("Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown." +
                "                Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness." +
                "                So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.<em>Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.</em>"));
guacRecipe.addDirections(new Direction("Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching\" +\n" +
        "                \" it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\\n\" +\n" +
        "                \"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving"));
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);


      guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoon));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoon));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoon));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoon));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dash));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setServings(4);

        //add to return list
        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);



        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacoNotes);
        tacosRecipe.addDirections(new Direction("Prepare a gas or charcoal grill for medium-high, direct heat."));
        tacosRecipe.addDirections(new Direction("Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, " +
                "garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n"));

        tacosRecipe.addDirections(new Direction("Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes"));
        tacosRecipe.addDirections(new Direction("Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\\n\" +\n" +
                "                \"Wrap warmed tortillas in a tea towel to keep them warm until serving."));
        tacosRecipe.addDirections(new Direction("Assemble the tacos: Slice the chicken into strips. \" +\n" +
                "                \"On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and \" +\n" +
                "                \"onion slices. Drizzle with the thinned sour cream. Serve with lime wedges"));


        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cups));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pint));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cups));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));
        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);
        tacosRecipe.setUrl("https://www.foodnetwork.com/recipes/bobby-flay/spice-rubbed-chicken-breast-tacos-with-grilled-poblanos-bbq-onions-and-coleslaw-recipe-1953280");
        recipes.add(tacosRecipe);
        tacosRecipe.setServings(5);

        return recipes;
    }
}