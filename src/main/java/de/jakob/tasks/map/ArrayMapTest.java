package de.jakob.tasks.map;

import java.util.HashSet;
import java.util.Set;

public class ArrayMapTest {

    public static void main(String[] args) {
        // Run all tests
        testPutAndGet();
        testUnknownKeyException();
        testResizeArray();
        testKeysAsSet();
        testGetValuesAsSetOrThrow();
        System.out.println("All tests passed!");
    }

    public static void testPutAndGet() {
        AbstractMap<String, Integer> map = new ArrayMap<>();
        map.put("key1", 100);
        map.put("key2", 200);

        try {
            assert map.getOrThrow("key1") == 100 : "Value for key1 should be 100";
            assert map.getOrThrow("key2") == 200 : "Value for key2 should be 200";
        } catch (UnknownKeyException e) {
            throw new AssertionError("Key should exist in the map", e);
        }
    }

    public static void testUnknownKeyException() {
        AbstractMap<String, Integer> map = new ArrayMap<>();
        try {
            map.getOrThrow("nonexistent");
            throw new AssertionError("Expected UnknownKeyException was not thrown");
        } catch (UnknownKeyException e) {
            // Expected behavior
        }
    }

    public static void testResizeArray() {
        AbstractMap<String, Integer> map = new ArrayMap<>();
        for (int i = 1; i <= 15; i++) {
            map.put("key" + i, i);
        }

        try {
            assert map.getOrThrow("key15") == 15 : "Value for key15 should be 15";
        } catch (UnknownKeyException e) {
            throw new AssertionError("Key should exist after resizing", e);
        }
    }

    public static void testKeysAsSet() {
        AbstractMap<String, Integer> map = new ArrayMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        Set<String> keys = map.keysAsSet();
        assert keys.contains("a") : "Key set should contain 'a'";
        assert keys.contains("b") : "Key set should contain 'b'";
        assert keys.contains("c") : "Key set should contain 'c'";
        assert keys.size() == 3 : "Key set should contain exactly 3 elements";
    }

    public static void testGetValuesAsSetOrThrow() {
        AbstractMap<String, Integer> map = new ArrayMap<>();
        map.put("key1", 10);
        map.put("key2", 20);

        Set<String> keys = new HashSet<>();
        keys.add("key1");
        keys.add("key2");

        try {
            Set<Integer> values = map.getValuesAsSetOrThrow(keys);
            assert values.contains(10) : "Values should contain 10";
            assert values.contains(20) : "Values should contain 20";
            assert values.size() == 2 : "Values set should contain exactly 2 elements";
        } catch (UnknownKeyException e) {
            throw new AssertionError("Exception should not be thrown for existing keys", e);
        }

        keys.add("nonexistent");
        try {
            map.getValuesAsSetOrThrow(keys);
            throw new AssertionError("Expected UnknownKeyException was not thrown");
        } catch (UnknownKeyException e) {
            // Expected behavior
        }
    }
}
