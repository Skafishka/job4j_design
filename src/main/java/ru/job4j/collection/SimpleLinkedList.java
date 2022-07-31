package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int modCount = 0;

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        modCount++;
        Node<E> temp = last;
        Node<E> newNode = new Node<E>(temp, value, null);
        last = newNode;
        if (temp == null) {
            first = newNode;
        } else {
            temp.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E value = null;
        if (index == 0) {
            value = first.item;
        } else if (index == size - 1) {
            value = last.item;
        } else if (index < size / 2) {
            Node<E> temp = first;
            for (int q = 0; q < index; q++) {
                temp = temp.next;
            }
            value = temp.item;
        } else if (index >= size / 2) {
            Node<E> temp = last;
            for (int q = size - 1; q > index; q--) {
                temp = temp.prev;
            }
        value = temp.item;
        }
        return value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            SimpleLinkedList.Node<E> current = first;

            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E prev = current.item;
                current = current.next;
                return prev;
            }
        };
    }
}