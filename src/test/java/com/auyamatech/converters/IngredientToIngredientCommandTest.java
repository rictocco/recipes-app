package com.auyamatech.converters;

import com.auyamatech.commands.IngredientCommand;
import com.auyamatech.domain.Ingredient;
import com.auyamatech.domain.Recipe;
import com.auyamatech.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;


public class IngredientToIngredientCommandTest {
    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal(100);
    private static final Long ID_VALUE = new Long(1L);
    private static final Long UOM_ID = new Long(1L);
    private static final String DESCRIPTION = "Description";

    private static final UnitOfMeasure UOM = new UnitOfMeasure();
    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertNullUOM() throws Exception {
        //given
        RECIPE.setId(ID_VALUE);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(null);
        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);
        //then
        assertNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(ID_VALUE, ingredientCommand.getRecipeId());
    }

    @Test
    public void convert() {
        //given
        RECIPE.setId(ID_VALUE);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure uomCommand = new UnitOfMeasure();
        uomCommand.setId(UOM_ID);
        ingredient.setUom(uomCommand);
        ingredient.setRecipe(RECIPE);

        //when
        IngredientCommand command = converter.convert(ingredient);

        //then
        assertNotNull(command);
        assertNotNull(command.getUom().getId());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(UOM_ID, command.getUom().getId());
        assertEquals(ID_VALUE, command.getRecipeId());
    }
}