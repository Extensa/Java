import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = { 6, 8, 3, 5, 2, 1, 7, 4, 9 };

        quickSort(arr,0,arr.length -1);
        System.out.println(Arrays.toString(arr));
    }

    static void insertionSort(int[] collection) {
        for (int i = 1; i < collection.length; i++) {
            int key = collection[i];
            int j = i - 1;

            while (j >= 0 && key < collection[j]) {
                collection[j + 1] = collection[j];
                j--;
            }

            collection[j + 1] = key;
        }
    }

    static void selectionSort(int[] collection) {
        for (int i = 0; i < collection.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < collection.length; j++) {
                if (collection[j] < collection[i]) {
                    minIndex = j;
                }
            }

            int temp = collection[i];
            collection[i] = collection[minIndex];
            collection[minIndex] = temp;
        }
    }

    //mergesort
    static int[] mergeSort(int[] collection) {
        if (collection.length == 1) {
            return collection;
        }

        int n = (int)Math.floor(collection.length / 2);
        int[] leftArr = new int[n];
        int[] rightArr = new int[collection.length - n];

        for (int i = 0; i < collection.length; i++) {
            if (i < n) {
                leftArr[i] = collection[i];
            }
            else {
                rightArr[i - n] = collection[i];
            }
        }

        leftArr = mergeSort(leftArr);
        rightArr = mergeSort(rightArr);
        collection = merge(leftArr, rightArr);

        return collection;
    }

    static int[] merge(int[] leftArr, int[] rightArr)
    {
        int[] resultArr = new int[leftArr.length + rightArr.length];
        int a = 0, b = 0;

        for(int i = 0; i < leftArr.length + rightArr.length; ++i)
        {
            if(a == leftArr.length)
            {
                resultArr[i] = rightArr[b];
                ++b;
            }
            else if(b == rightArr.length)
            {
                resultArr[i] = leftArr[a];
                ++a;
            }
            else if(leftArr[a]>rightArr[b])
            {
                resultArr[i] = rightArr[b];
                ++b;
            }
            else
            {
                resultArr[i] = leftArr[a];
                ++a;
            }
        }

        return resultArr;
    }

    //quicksort
    static int partition(int arr[], int left, int right)
    {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        return i;
    }

    static void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);

        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }
}
