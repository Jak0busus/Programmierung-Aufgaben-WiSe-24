package de.jakob.tasks.map;

public class Entry<K, V> {
    private final K key;
    private final V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    //record class would be appropriate: public record Entry<K, V>(K key, V value) { }
    //but task asks for normal class
}
