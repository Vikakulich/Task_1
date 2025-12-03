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
 * Тесты для класса Bun.
 * Использует параметризацию для тестирования различных булочек.
 */
@RunWith(Parameterized.class)
public class BunTests {

    private final String name;
    private final float price;
    private Bun bun;

    public BunTests(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{0}: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "White Bun", 100.0f },
                { "Black Bun", 120.5f },
                { "Sesame Bun", 110.25f },
                { "Whole Wheat Bun", 130.0f },
        });
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void testBunNameGetter() {
        assertEquals("Название должно совпадать", name, bun.getName());
    }

    @Test
    public void testBunPriceGetter() {
        assertEquals("Цена должна совпадать", price, bun.getPrice(), 0.01f);
    }

    @Test
    public void testBunConstructor() {
        assertNotNull("Булочка должна быть создана", bun);
        assertEquals("Название должно быть установлено", name, bun.name);
        assertEquals("Цена должна быть установлена", price, bun.price, 0.01f);
    }

    @Test
    public void testBunNameNotNull() {
        assertNotNull("Название булочки не должно быть null", bun.getName());
    }

    @Test
    public void testBunPricePositive() {
        assertEquals("Цена должна быть положительной", price > 0, price > 0);
    }
}

