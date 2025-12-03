package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тесты для класса Burger.
 * Использует моки для изоляции компонентов.
 */
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockBun.getName()).thenReturn("Test Bun");

        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient1.getName()).thenReturn("Beef");
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);

        when(mockIngredient2.getPrice()).thenReturn(25.0f);
        when(mockIngredient2.getName()).thenReturn("Mayo");
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals("Булочка должна быть установлена", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals("Один ингредиент должен быть добавлен", 1, burger.ingredients.size());
        assertTrue("Ингредиент должен содержаться в списке", burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void testAddMultipleIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        assertEquals("Два ингредиента должны быть добавлены", 2, burger.ingredients.size());
        assertTrue("Первый ингредиент должен содержаться", burger.ingredients.contains(mockIngredient1));
        assertTrue("Второй ингредиент должен содержаться", burger.ingredients.contains(mockIngredient2));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(0);
        assertEquals("Один ингредиент должен остаться", 1, burger.ingredients.size());
        assertEquals("Оставшийся ингредиент должен быть второй", mockIngredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(0, 1);
        assertEquals("После перемещения должно остаться 2 ингредиента", 2, burger.ingredients.size());
        assertEquals("На первой позиции должен быть второй ингредиент", mockIngredient2, burger.ingredients.get(0));
        assertEquals("На второй позиции должен быть первый ингредиент", mockIngredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceWithoutIngredients() {
        burger.setBuns(mockBun);
        float expectedPrice = 100.0f * 2;
        assertEquals("Цена должна быть суммой двух булочек", expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetPriceWithIngredients() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        float expectedPrice = 100.0f * 2 + 50.0f + 25.0f;
        assertEquals("Цена должна включать булочки и ингредиенты", expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        String receipt = burger.getReceipt();
        assertNotNull("Чек не должен быть null", receipt);
        assertTrue("Чек должен содержать название булочки", receipt.contains("Test Bun"));
        assertTrue("Чек должен содержать название первого ингредиента", receipt.contains("Beef"));
        assertTrue("Чек должен содержать название второго ингредиента", receipt.contains("Mayo"));
        assertTrue("Чек должен содержать цену", receipt.contains("Price:"));
    }

    @Test
    public void testGetReceiptFormat() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        String receipt = burger.getReceipt();
        assertTrue("Чек должен начинаться с верхней границы", receipt.startsWith("(===="));
        assertTrue("Чек должен содержать нижнюю границу", receipt.contains("(===="));
        assertTrue("Чек должен содержать информацию о цене", receipt.contains("Price:"));
    }

    @Test
    public void testBurgerInitialization() {
        Burger testBurger = new Burger();
        assertNotNull("Список ингредиентов должен быть инициализирован", testBurger.ingredients);
        assertEquals("Список ингредиентов должен быть пустым", 0, testBurger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientAtIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(1);
        assertEquals("Один ингредиент должен остаться", 1, burger.ingredients.size());
        assertEquals("Оставшийся ингредиент должен быть первый", mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientBackward() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(1, 0);
        assertEquals("После перемещения должно остаться 2 ингредиента", 2, burger.ingredients.size());
        assertEquals("На первой позиции должен быть второй ингредиент", mockIngredient2, burger.ingredients.get(0));
        assertEquals("На второй позиции должен быть первый ингредиент", mockIngredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceCallsMockBunPrice() {
        burger.setBuns(mockBun);
        burger.getPrice();
        verify(mockBun).getPrice();
    }

    @Test
    public void testGetReceiptCallsMockBunName() {
        burger.setBuns(mockBun);
        burger.getReceipt();
        verify(mockBun, times(2)).getName();
    }
}

