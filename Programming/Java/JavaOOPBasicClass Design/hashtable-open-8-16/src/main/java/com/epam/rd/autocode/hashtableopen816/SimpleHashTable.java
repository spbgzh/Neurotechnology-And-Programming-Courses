package com.epam.rd.autocode.hashtableopen816;

public class SimpleHashTable implements HashtableOpen8to16 {
    private static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY = 16;
    private static final int MULTIPLIER_TO_INCREASE_CAPACITY = 2;
    private static final int DIVISOR_TO_DECREASE_CAPACITY = 2;
    private static final int SIZE_CAPACITY_RATIO_TO_DECREASE = 4;
    private Entry[] table;
    private int size;
    private int searchCounter;

    public SimpleHashTable() {
        table = new Entry[INITIAL_CAPACITY];
    }

    @Override
    public void insert(final int key, final Object value) {
        increaseCapacityIfNeed(key);
        int hashCode = Integer.valueOf(key).hashCode();
        int index = indexFor(hashCode);
        addEntry(key, value, index);
    }

    private void increaseCapacityIfNeed(final int key) {
        if (size == table.length && search(key) == null) {
            if (table.length != MAX_CAPACITY) {
                changeCapacity(table.length * MULTIPLIER_TO_INCREASE_CAPACITY);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public Object search(final int key) {
        int hashCode = Integer.valueOf(key).hashCode();
        int index = indexFor(hashCode);
        searchCounter = 0;
        Entry entry = checkBucket(key, index);
        if (entry != null) {
            return entry.value;
        } else return null;
    }

    private Entry checkBucket(final int key, final int index) {
        if (searchCounter++ == table.length) {
            return null;
        }
        if (table[index] != null) {
            if (table[index].key == key && table[index].notRemoved()) {
                return table[index];
            } else {
                return checkBucket(key, indexLinerIncrease(index));
            }
        }
        return null;
    }

    private void changeCapacity(final int newCapacity) {
        Entry[] oldTable = table;
        table = new Entry[newCapacity];
        size = 0;
        for (Entry entry : oldTable) {
            if (entry != null && entry.notRemoved()) {
                insert(entry.key, entry.value);
            }
        }
    }

    private int indexFor(final int hashCode) {
        return Math.abs(hashCode) % table.length;
    }

    private void addEntry(final int key, final Object value, final int index) {
        if (table[index] != null && table[index].notRemoved()) {
            if (table[index].key == key) {
                table[index].value = value;
            } else {
                addEntry(key, value, indexLinerIncrease(index));
            }
        } else {
            table[index] = new Entry(key, value);
            size++;
        }
    }

    private int indexLinerIncrease(int index) {
        index++;
        if (index != table.length) {
            return index;
        } else {
            return 0;
        }
    }

    @Override
    public void remove(final int key) {
        int hashCode = Integer.valueOf(key).hashCode();
        int index = indexFor(hashCode);
        searchCounter = 0;
        Entry entry = checkBucket(key, index);
        if (entry != null) {
            entry.remove();
            size--;
            decreaseSizeIfNeed();
        }
    }

    private void decreaseSizeIfNeed() {
        if (size != 0 && size * SIZE_CAPACITY_RATIO_TO_DECREASE <= table.length) {
            changeCapacity(table.length / DIVISOR_TO_DECREASE_CAPACITY);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] keys() {
        int[] keys = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].notRemoved()) {
                keys[i] = table[i].key;
            }
        }
        return keys;
    }

    private static class Entry {
        int key;
        Object value;
        boolean isRemoved;

        public Entry(final int key, final Object value) {
            this.key = key;
            this.value = value;
        }

        public void remove() {
            isRemoved = true;
        }

        public boolean notRemoved() {
            return !isRemoved;
        }
    }
}
