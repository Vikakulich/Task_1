package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Тесты для перечисления IngredientType.
 * Проверяет наличие типов ингредиентов и их доступность.
 */
public class IngredientTypeTests {

    @Test
    public void testSauceTypeExists() {
        IngredientType sauce = IngredientType.SAUCE;
        assertNotNull("SAUCE type должен существовать", sauce);
        assertEquals("SAUCE type должен быть SAUCE", IngredientType.SAUCE, sauce);
    }

    @Test
    public void testFillingTypeExists() {
        IngredientType filling = IngredientType.FILLING;
        assertNotNull("FILLING type должен существовать", filling);
        assertEquals("FILLING type должен быть FILLING", IngredientType.FILLING, filling);
    }

    @Test
    public void testIngredientTypesCount() {
        IngredientType[] types = IngredientType.values();
        assertEquals("Должно быть 2 типа ингредиентов", 2, types.length);
    }
}

