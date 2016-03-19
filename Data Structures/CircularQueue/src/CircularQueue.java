import com.sun.deploy.util.ArrayUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class CircularQueue<E> implements Iterable<E> {
    private final int INITIAL_SIZE = 16;

    private E[] arr;
    private int count;
    private int startIndex;
    private int endIndex;

    public CircularQueue() {
        this.arr = (E[])new Object[INITIAL_SIZE];
        this.count = 0;
        this.startIndex = 0;
        this.endIndex = 0;
    }

    public int getCount() {
        return count;
    }

    private void grow() {
        E[] newArr = (E[])new Object[this.arr.length * 2];
        this.copyArr(newArr);
        this.arr = newArr;
        this.startIndex = 0;
        this.endIndex = this.count;
    }

    private void copyArr(E[] newArr) {
        int sourceIndex = this.startIndex;
        int destinationIndex = 0;

        for (int i = 0; i < this.count; i++) {
            newArr[destinationIndex] = this.arr[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.arr.length;
            destinationIndex++;
        }
    }

    public void enqueue(E item) {
        if (this.count >= this.arr.length) {
            this.grow();
        }

        this.arr[endIndex] = item;
        this.endIndex = (this.endIndex + 1) % this.arr.length;
        this.count++;
    }

    public E dequeue() {
        if (this.count == 0) {
            throw new IllegalStateException("The queue is empty!");
        }

        E result = this.arr[startIndex];
        this.arr[startIndex] = null;
        this.startIndex = (this.startIndex + 1) % this.arr.length;
        this.count--;
        return result;
    }

    public String toString() {
        StringBuilder r = new StringBuilder();

        r.append(Arrays.toString(this.arr));
        r.append("\nstartIndex: ").append(this.startIndex);
        r.append("\nendIndex: ").append(this.endIndex);
        r.append("\nCount: ").append(this.count);

        return r.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularQueueIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        action.accept(iterator().next());
    }

    private class CircularQueueIterator implements Iterator<E>{
        private int head = startIndex;
        private int tail = endIndex;
        private int lastReturned = -1;

        @Override
        public boolean hasNext() {
            return head != tail;
        }

        @Override
        public E next() {
            if (head == tail)
                throw new NoSuchElementException();
            @SuppressWarnings("unchecked")
            E result = (E) arr[head];
            // This check doesn't catch all possible comodifications,
            // but does catch the ones that corrupt traversal
            if (endIndex != tail || result == null)
                throw new ConcurrentModificationException();
            lastReturned = head;
            head = (head + 1) & (arr.length - 1);
            return result;
        }
    }
}
