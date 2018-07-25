package com.chukanwobi.recipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
Category category;
@Before
public void setUp(){
category =  new Category();
}
    @Test
    public void getId() {
    Long idVallue= 4l;
    category.setId(idVallue);
    assertEquals(idVallue,category.getId());
    }

    @Test
    public void getDescription() {
    String description = "I am sexy and I know it";
    category.setDescription(description);
    assertEquals(description,category.getDescription());
    }

    @Test
    public void getRecipes() {
    }
}