package com.seafooddelakec.test.menu;

import com.seafooddelakec.menu.MenuItem;
import com.seafooddelakec.menu.MenuItemLoader;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemLoaderTest {

    private MenuItemLoader loader;

    @BeforeEach
    void setUp() {
        loader = new MenuItemLoader();
    }

    @Test
    void testLoadMenu_Success() {
        List<MenuItem> menuItems = loader.loadMenu();

        assertEquals(9, menuItems.size());
        assertEquals(new MenuItem(1, 17.00, "0.5lb Shrimp & 0.5lb Crawfish", MenuItem.Type.COMBO), menuItems.get(0));
        assertEquals(new MenuItem(2, 24.00, "0.5lb Shrimp & 1 Cluster Snow Crab Legs", MenuItem.Type.COMBO), menuItems.get(1));
        assertEquals(new MenuItem(6, 2.00, "Water", MenuItem.Type.DRINK), menuItems.get(5));
        assertEquals(new MenuItem(9, 12.00, "Mixed Drink", MenuItem.Type.DRINK), menuItems.get(8));
    }

    @Test
    void testLoadMenu_VerifyAllItems() {
        List<MenuItem> menuItems = loader.loadMenu();

        MenuItem[] expectedItems = {
                new MenuItem(1, 17.00, "0.5lb Shrimp & 0.5lb Crawfish", MenuItem.Type.COMBO),
                new MenuItem(2, 24.00, "0.5lb Shrimp & 1 Cluster Snow Crab Legs", MenuItem.Type.COMBO),
                new MenuItem(3, 27.00, "0.5lb Shrimp & 1 Cluster King Crab", MenuItem.Type.COMBO),
                new MenuItem(4, 27.00, "1lb Shrimp & 1 Cluster Snow Crab Legs", MenuItem.Type.COMBO),
                new MenuItem(5, 27.00, "6oz Lobster Tail & 1 Cluster Snow Crab Legs", MenuItem.Type.COMBO),
                new MenuItem(6, 2.00, "Water", MenuItem.Type.DRINK),
                new MenuItem(7, 5.00, "Soda", MenuItem.Type.DRINK),
                new MenuItem(8, 8.00, "Beer", MenuItem.Type.DRINK),
                new MenuItem(9, 12.00, "Mixed Drink", MenuItem.Type.DRINK)
        };

        assertArrayEquals(expectedItems, menuItems.toArray(new MenuItem[0]));
    }

    @Test
    void testLoadMenu_VerifyCombos() {
        List<MenuItem> menuItems = loader.loadMenu();
        List<MenuItem> combos = new ArrayList<>();

        for (MenuItem item : menuItems) {
            if (item.type() == MenuItem.Type.COMBO) {
                combos.add(item);
            }
        }

        assertEquals(5, combos.size());

        boolean allCombos = true;
        for (MenuItem item : combos) {
            if (item.type() != MenuItem.Type.COMBO) {
                allCombos = false;
                break;
            }
        }
        assertTrue(allCombos);
    }

    @Test
    void testLoadMenu_VerifyDrinks() {
        List<MenuItem> menuItems = loader.loadMenu();
        List<MenuItem> drinks = new ArrayList<>();

        for (MenuItem item : menuItems) {
            if (item.type() == MenuItem.Type.DRINK) {
                drinks.add(item);
            }
        }

        assertEquals(4, drinks.size());

        boolean allDrinks = true;
        for (MenuItem item : drinks) {
            if (item.type() != MenuItem.Type.DRINK) {
                allDrinks = false;
                break;
            }
        }
        assertTrue(allDrinks);
    }
}