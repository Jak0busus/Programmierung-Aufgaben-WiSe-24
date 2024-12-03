package de.jakob.tasks.map;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMap<K, V> {

    public abstract V getOrThrow(K key) throws UnknownKeyException;

    public abstract void put(K key, V value);

    public abstract Set<K> keysAsSet();

    public Set<V> getValuesAsSetOrThrow(Set<K> keys) throws UnknownKeyException {
        HashSet<V> values = new HashSet<V>();
        for (K key : keys) {
            values.add(getOrThrow(key));
        }
        return values;
    }

}
