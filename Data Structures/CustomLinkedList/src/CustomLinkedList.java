import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class CustomLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int count;

    public CustomLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    public int getCount() {
        return this.count;
    }

    public T getFirst() {
        return head.getValue();
    }

    public T getLast() {
        return tail.getValue();
    }

    Node<T> getNode(int index) {
        this.checkIfEmpty();

        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }

        if (index < (count / 2)) {
            Node<T> desiredNode = this.head;
            for (int i = 0; i < index; i++) {
                desiredNode = desiredNode.getNextNode();
            }
            return desiredNode;
        }
        else {
            Node<T> desiredNode = this.tail;
            for (int i = this.count - 1; i > index; i--) {
                desiredNode = desiredNode.getPrevNode();
            }
            return desiredNode;
        }
    }

    private void checkIfEmpty() {
        if (this.count == 0) {
            throw new IllegalStateException("List is empty!");
        }
    }

    public T get(int index) {
        return this.getNode(index).getValue();
    }

    public void addFirst(T item) {
        if (this.count == 0) {
            this.head = this.tail = new Node<T>(item);
        } else {
            Node<T> newHead = new Node<T>(item);
            newHead.setNextNode(this.head);
            this.head.setPrevNode(newHead);
            this.head = newHead;
        }

        this.count++;
    }

    public void addLast(T item) {
        if (this.count == 0) {
            this.head = this.tail = new Node<T>(item);
        } else {
            Node<T> newTail = new Node<>(item);
            newTail.setPrevNode(this.tail);
            this.tail.setNextNode(newTail);
            this.tail = newTail;
        }

        this.count++;
    }

    public T removeFirst() {
        checkIfEmpty();

        T firstElement = this.getFirst();
        this.head = this.head.getNextNode();
        if (this.head != null) {
            this.head.setPrevNode(null);
        } else {
            this.tail = null;
        }

        this.count--;
        return firstElement;
    }

    public T removeLast() {
        checkIfEmpty();

        T lastElement = this.getLast();
        this.tail = this.tail.getPrevNode();
        if (this.tail != null) {
            this.tail.setNextNode(null);
        } else {
            this.head = null;
        }

        this.count--;
        return lastElement;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> itr = new Iterator<T>() {

            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public T next() {
                T temp = this.current.getValue();
                this.current = this.current.getNextNode();
                return temp;
            }
        };

        return itr;
    }
}



