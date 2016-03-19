import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int[] numbers = new int[2];

        getCombinationsRepetition(numbers, 0, 1, n);
    }

    static void recursiveNestedLoops(int[] numbers,int index, int n) {
        if (index >= numbers.length) {
            System.out.println(Arrays.toString(numbers));
        }
        else {
            for (int i = 1; i <= n; i++) {
                numbers[index] = i;
                recursiveNestedLoops(numbers, index + 1, n);
            }
        }
    }

    static void getCombinationsRepetition(int[] numbers, int index, int startNum, int endNum) {
        if (index >= numbers.length) {
            System.out.println(Arrays.toString(numbers));
        }
        else {
            for (int i = startNum; i <= endNum; i++) {
                numbers[index] = i;
                getCombinationsRepetition(numbers, index + 1, i, endNum);
            }
        }
    }

    static void getCombinationsWithoutRepetition(int[] numbers, int index, int startNum, int endNum) {
        if (index >= numbers.length) {
            System.out.println(Arrays.toString(numbers));
        }
        else {
            for (int i = startNum; i <= endNum; i++) {
                numbers[index] = i;
                getCombinationsRepetition(numbers, index + 1, i + 1, endNum);
            }
        }
    }
}
