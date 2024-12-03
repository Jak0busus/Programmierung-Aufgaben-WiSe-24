package de.jakob.tasks.map;

import java.util.HashSet;
import java.util.Set;

import static de.jakob.tasks.map.GenericArrayHelper.*;

public class ArrayMap<K, V> extends AbstractMap<K, V> {

    Entry<K, V>[] entries;

    public ArrayMap(Entry<K, V>[] entries) {
        this.entries = copyArray(entries);
    }

    public ArrayMap() {
        this.entries = newEntryArrayOfSize(10);
    }

    @Override
    public V getOrThrow(K key) throws UnknownKeyException {
        for (Entry<K, V> entry : entries) {
            if (entry != null && entry.getKey().equals(key)) return entry.getValue();
        }
        throw new UnknownKeyException();
    }

    @Override
    public void put(K key, V value) {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null || entries[i].getKey().equals(key)) {
                entries[i] = new Entry<>(key, value);
                return;
            }
        }
        entries = copyArrayWithIncreasedSize(entries, entries.length * 2);
        entries[entries.length / 2 + 1] = new Entry<>(key, value);
    }

    @Override
    public Set<K> keysAsSet() {
        HashSet<K> keys = new HashSet<K>();
        for (Entry<K, V> entry : entries) {
            if (entry != null) keys.add(entry.getKey());
        }
        return keys;
    }
}
