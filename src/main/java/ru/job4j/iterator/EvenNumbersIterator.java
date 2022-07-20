package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean check() {
        while (index < data.length && data[index] % 2 != 0) {
            index++;
        } return true;
    }

    @Override
    public boolean hasNext() {
        if (check()) {
            return index < data.length;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (check()) {
            return data[index++];
        }
        return null;
    }
}
