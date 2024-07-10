package evlitvin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for CustomHashMap")
class CustomHashMapTest {

    private CustomHashMap customHashMap;

    @BeforeEach
    void setUp() {
        customHashMap = new CustomHashMap();
        customHashMap.put("key1", "value1");
        customHashMap.put("key2", "value2");
    }

    @Test
    @DisplayName("Put key-value pairs to CustomHashMap")
    void put() {
        assertEquals(2, customHashMap.size());
    }

    @Test
    @DisplayName("Get right value by key from CustomHashMap")
    void get() {
        assertEquals("value1", customHashMap.get("key1"));
    }

    @Test
    @DisplayName("Get key set from CustomHashMap")
    void keySet() {
        assertFalse(customHashMap.keySet().isEmpty());
    }

    @Test
    @DisplayName("Get values list from CustomHashMap")
    void values() {

        assertFalse(customHashMap.values().isEmpty());
        assertTrue(customHashMap.values().contains(customHashMap.get("key1")));
        assertEquals("value2", customHashMap.get("key2"));
    }

    @Test
    @DisplayName("Remove key-value pair from CustomHashMap")
    void remove() {
        customHashMap.remove("key1");

        assertEquals(1, customHashMap.size());
        assertNull(customHashMap.get("key1"));
    }

    @Test
    @DisplayName("Put two key-value pairs to CustomHashMap and check size")
    void size() {
        assertEquals(2, customHashMap.size());
    }

    @Test
    @DisplayName("Delete all key-value pairs from CustomHashMap and check is empty")
    void clear() {
        customHashMap.clear();

        assertEquals(0, customHashMap.size());
    }

    @Test
    @DisplayName("Increase CustomHashMap capacity by doubling")
    void increase() {
        for (int i = 0; i < 150; i++) {
            customHashMap.put("key" + i, "value" + i);
        }
        assertEquals(150, customHashMap.size());
    }
}