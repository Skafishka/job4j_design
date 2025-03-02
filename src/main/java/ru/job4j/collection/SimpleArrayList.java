package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void grow(T[] container) {
        this.container = Arrays.copyOf(container, container.length * 2);
        if (this.container.length == 0) {
            this.container = Arrays.copyOf(container, 10);
        }
    }

    @Override
    public void add(T value) {
        modCount++;
        if (container.length == size) {
            grow(this.container);
        }
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        T t = get(index);
        container[index] = newValue;
        return t;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T t = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        return t;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int q = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return q < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[q++];
            }
        };
    }

}