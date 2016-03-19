import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class ArrayStack<T> {
    private final int INITIAL_CAPACITY = 16;

    private T[] arr;
    private int count;

    public ArrayStack() {
        this.arr = (T[]) new Object[INITIAL_CAPACITY];
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    private void grow(){
        T[] newArr = (T[]) new Object[this.arr.length * 2];
        System.arraycopy(this.arr, 0, newArr, 0, this.count);
        this.arr = newArr;
    }

    public void push(T item) {
        if (this.count >= this.arr.length) {
            this.grow();
        }

        this.arr[this.count] = item;
        this.count++;
    }

    public T pop() {
        if (this.count == 0) {
            throw new IllegalStateException("The stack is empty!");
        }

        T result = this.arr[this.count - 1];
        this.arr[this.count - 1] = null;
        this.count--;

        return result;
    }

    public String toString() {
        String result = Arrays.toString(this.arr);

        return result;
    }
}
