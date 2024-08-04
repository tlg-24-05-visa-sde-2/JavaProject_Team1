package com.seafooddelakec.test.menu;

import com.seafooddelakec.menu.Menu;
import com.seafooddelakec.menu.MenuItem;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu menu;
    private List<MenuItem> testMenuItems;

    @BeforeEach
    void setUp() {
        testMenuItems = Arrays.asList(
                new MenuItem(1, 17.00, "Combo 1", MenuItem.Type.COMBO),
                new MenuItem(2, 24.00, "Combo 2", MenuItem.Type.COMBO),
                new MenuItem(3, 2.00, "Drink 1", MenuItem.Type.DRINK),
                new MenuItem(4, 5.00, "Drink 2", MenuItem.Type.DRINK)
        );
        menu = new Menu(testMenuItems);
    }

    @Test
    void testGetItemById_ExistingItem() {
        MenuItem item = menu.getItemById(2);
        assertNotNull(item);
        assertEquals(2, item.id());
        assertEquals("Combo 2", item.description());
    }

    @Test
    void testGetItemById_NonExistingItem() {
        MenuItem item = menu.getItemById(10);
        assertNull(item);
    }

    @Test
    void testAddOrderedItem() {
        MenuItem item = testMenuItems.get(0);
        menu.addOrderedItem(item);
        List<MenuItem> orderedItems = menu.getOrderedItems();
        assertEquals(1, orderedItems.size());
        assertEquals(item, orderedItems.get(0));
    }

    @Test
    void testGetOrderedItems_ReturnsCopy() {
        MenuItem item = testMenuItems.get(0);
        menu.addOrderedItem(item);
        List<MenuItem> orderedItems = menu.getOrderedItems();
        orderedItems.add(testMenuItems.get(1));
        assertEquals(1, menu.getOrderedItems().size());
    }
}