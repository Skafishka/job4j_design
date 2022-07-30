package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public void grow(T[] container) {
        this.container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        modCount++;
        if (container.length <= size) {
            grow(this.container);
        } else {
            add(value);
            size++;
        }
    }

    @Override
    public T set(int index, T newValue) {
        modCount++;
        if (Objects.checkIndex(index, container.length) != index) {
            throw new IndexOutOfBoundsException();
        } else {
            return set(index, newValue);
        }
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return false;
            }

            @Override
            public T next() {
                return null;
            }

        };
    }
}