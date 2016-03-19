import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class CustomList<T> implements Iterable<T> {
    private static final int INITIAL_SIZE = 16;
    private T[] arr;
    private int count;

    public CustomList() {
        this.arr = (T[])new Object[INITIAL_SIZE];
    }

    public int getCount() {
        return this.count;
    }

    public T get(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }

        return this.arr[index];
    }

    public void add(T item) {
        int arrLength = this.arr.length;

        if (arrLength == this.count) {
            T[] newArr = (T[]) new Object[arrLength * 2];
            System.arraycopy(this.arr, 0, newArr, 0, arrLength);
            this.arr = newArr;

            this.arr[this.count] = item;
            this.count++;
        }
        else {
            this.arr[this.count] = item;
            this.count++;
        }
    }

    public T find(T item) {
        T result = null;

        if (this.count == 0) {
            return result;
        }
        else {
            for (int i = 0; i < this.count; i++) {
                if (this.arr[i].equals(item)) {
                    result = this.arr[i];
                    break;
                }
            }
        }

        return result;
    }

    public int indexOf(T item) {
        int index = -1;

        if (this.count == 0) {
            return index;
        }
        else {
            for (int i = 0; i < this.count; i++) {
                if (this.arr[i].equals(item)) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    public boolean remove(T item) {
        boolean isRemoved = false;

        if (this.count == 0) {
            return isRemoved;
        }
        else {
            int index = this.indexOf(item);
            if (index != -1) {
                T[] newArr = (T[])new Object[this.arr.length];

                for (int i = index; i < this.count - 1; i++) {
                    this.arr[i] = this.arr[i + 1];
                }
                this.arr[this.count - 1] = null;
                this.count--;
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> itr = new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < count;
            }

            @Override
            public T next() {
                return arr[this.currentIndex++];
            }
        };

        return itr;
    }

    @Override
    public String toString(){
        String result = Arrays.toString(this.arr);

        return result;
    }
}
