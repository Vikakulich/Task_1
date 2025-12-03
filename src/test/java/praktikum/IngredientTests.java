package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Тесты для класса Ingredient.
 * Использует параметризацию для тестирования различных ингредиентов.
 */
@RunWith(Parameterized.class)
public class IngredientTests {

    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientTests(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{0}: {1} - {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { IngredientType.SAUCE, "Spicy Sauce", 100.5f },
                { IngredientType.SAUCE, "Mayo", 50.0f },
                { IngredientType.FILLING, "Beef", 200.0f },
                { IngredientType.FILLING, "Cheese", 150.5f },
                { IngredientType.FILLING, "Lettuce", 25.0f },
        });
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void testIngredientNameGetter() {
        assertEquals("Название должно совпадать", name, ingredient.getName());
    }

    @Test
    public void testIngredientPriceGetter() {
        assertEquals("Цена должна совпадать", price, ingredient.getPrice(), 0.01f);
    }

    @Test
    public void testIngredientTypeGetter() {
        assertEquals("Тип должен совпадать", type, ingredient.getType());
    }

    @Test
    public void testIngredientConstructor() {
        assertNotNull("Ингредиент должен быть создан", ingredient);
        assertEquals("Тип должен быть установлен", type, ingredient.type);
        assertEquals("Название должно быть установлено", name, ingredient.name);
        assertEquals("Цена должна быть установлена", price, ingredient.price, 0.01f);
    }
}

