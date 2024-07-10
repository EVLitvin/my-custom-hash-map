package evlitvin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code CustomHashMap} class implements {@code CustomMap} interface and provides methods to work with key-value pairs.
 * @param <K> the key of key-value pair
 * @param <V> the value of key-value pair
 *
 * @author Evgenii Litvin
 */

public class CustomHashMap<K, V> implements CustomMap<K, V> {

    /**
     * Initial capacity of array.
     */
    private static final int INITIAL_CAPACITY = 16;
    /**
     *  The value of load of array.
     */
    private static final double LOAD_FACTOR = 0.75;

    /**
     * Initial size of CustomHashMap.
     */
    private int size = 0;
    /**
     * Instantiation of array with INITIAL_CAPACITY as size of array
     */
    private Entry[] entries = (Entry[]) Array.newInstance(Entry.class, INITIAL_CAPACITY);

    /**
     * Method provides possibility to put key-value pair into CustomHashMap.
     * @param key the key of key-value pair.
     * @param value the value of key-value pair.
     */
    @Override
    public void put(K key, V value) {
        if (size >= (entries.length * LOAD_FACTOR)) {
            entriesDoubling();
        }
        boolean put = put(key, value, entries);
        if (put) {
            size++;
        }
    }

    /**
     * Method provides possibility to get key-value pair by key.
     * @param key the key of key-value pair which you want to find.
     * @return value of key-value pair if existed and null if there is no such key-value pair.
     */
    @Override
    public V get(K key) {
        int elementPosition = getElementPosition(key, entries.length);
        Entry entry = entries[elementPosition];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.emptyEntry;
        }
        return null;
    }

    /**
     * Returns Set of keys which are contained in CustomHashMap.
     * @return Set of keys.
     */
    @Override
    public Set<K> keySet() {
        Set<K> kSet = new HashSet<>();
        for (Entry entry : entries) {
            while (entry != null) {
                kSet.add(entry.key);
                entry = entry.emptyEntry;
            }
        }
        return kSet;
    }

    /**
     * Returns List of values which are contained in CustomHashMap.
     * @return List of values.
     */
    @Override
    public List<V> values() {
        List<V> vList = new ArrayList<>();
        for (Entry entry : entries) {
            while (entry != null) {
                vList.add(entry.value);
                entry = entry.emptyEntry;
            }
        }
        return vList;
    }

    /**
     * Delete key-value pair from CustomHashMap if existed.
     * @param key the key of key-value pair.
     * @return true if CustomHashMap contains such key-value which found by key and this key-value pair was deleted.
     * Return false if CustomHashMap not contains such key-value.
     */
    @Override
    public boolean remove(K key) {
        int elementPosition = getElementPosition(key, entries.length);
        Entry entry = entries[elementPosition];
        if (entry != null && entry.key.equals(key)) {
            entries[elementPosition] = entry.emptyEntry;
            size--;
            return true;
        } else {
            while (entry != null) {
                Entry nextEntry = entry.emptyEntry;
                if (nextEntry == null) {
                    return false;
                }
                if (nextEntry.key.equals(key)) {
                    entry.emptyEntry = nextEntry.emptyEntry;
                    size--;
                }
                entry = entry.emptyEntry;
            }
        }
        return false;
    }

    /**
     * Returns count of key-value pairs.
     * @return count of key-value pairs in CustomHashMap.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Delete all key-value pairs in CustomHashMap, set size to zero and initialize new empty array with INITIAL_CAPACITY.
     */
    @Override
    public void clear() {
        size = 0;
        entries = (Entry[]) Array.newInstance(Entry.class, INITIAL_CAPACITY);
    }

    /**
     * Method provides possibility of put key-value pair in case of collision.
     * @param key the key of key-value pair.
     * @param value the value of key-value pair.
     * @param entries existed array of entries.
     */
    private boolean put(K key, V value, Entry[] entries) {
        int position = getElementPosition(key, entries.length);
        Entry existedElement = entries[position];
        if (existedElement == null) {
            Entry entry = new Entry(key, value, null);
            entries[position] = entry;
            return true;
        } else {
            while (true) {
                if (existedElement.key.equals(key)) {
                    existedElement.value = value;
                    return false;
                }
                if (existedElement.emptyEntry == null) {
                    existedElement.emptyEntry = new Entry(key, value, null);
                    return true;
                }
                existedElement = existedElement.emptyEntry;
            }
        }
    }

    /**
     * Increase capacity of array by doubling it and copy entries from old array to new one.
     */
    private void entriesDoubling() {
        Entry[] newEntries = (Entry[]) Array.newInstance(Entry.class, entries.length * 2);
        for (Entry entry : entries) {
            Entry oldEntry = entry;
            while (oldEntry != null) {
                put(oldEntry.key, oldEntry.value, newEntries);
                oldEntry = oldEntry.emptyEntry;
            }
        }
    }

    /**
     * Returns index (position) of on element in array.
     * @param key the key of key-value pair.
     * @param arrayLength the length of array.
     * @return index (position) of on element in array.
     */
    private int getElementPosition(K key, int arrayLength) {
        return Math.abs(key.hashCode() % arrayLength);
    }

    private class Entry {

        private K key;
        private V value;
        private Entry emptyEntry;

        public Entry(K key, V value, Entry emptyEntry) {
            this.key = key;
            this.value = value;
            this.emptyEntry = emptyEntry;
        }
    }

}
