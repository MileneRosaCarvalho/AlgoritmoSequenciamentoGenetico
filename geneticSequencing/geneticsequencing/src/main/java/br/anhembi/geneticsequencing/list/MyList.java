package br.anhembi.geneticsequencing.list;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MyList <T> {
    private Node<T> first;
    private int size;

    public MyList() {
        this.first = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
        } else {
            Node<T> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public List<T> allElements() {
        List<T> result = new ArrayList<>();
        Node<T> current = first;
        while (current != null) {
            result.add(current.data);
            current = current.next;
        }
        return result;
    }

    public void remove() {
        if (first != null) {
            first = first.next;
            size--;
        }
    }

    public int size() {
        return size;
    }
}