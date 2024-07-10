package evlitvin;

import java.util.List;
import java.util.Set;

/**
 * It's a custom interface for custom HashMap realization.
 * @param <K> the key of key-value pair.
 * @param <V> the value of key-value pair.
 */
public interface CustomMap<K, V> {

    void put(K key, V value);
    V get(K key);
    Set<K> keySet();
    List<V> values();
    boolean remove(K key);
    int size();
    void clear();

}