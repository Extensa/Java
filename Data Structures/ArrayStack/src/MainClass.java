public class MainClass {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        String expression = "4 * 2 - ((23 + (3 ^ 4)) - (120 + 3))";

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '(') {
                stack.push(i);
            }
            else if (ch == ')') {
                int startIndex = stack.pop();
                int endIndex = i + 1;
                String brackets = expression.substring(startIndex, endIndex);

                System.out.println(brackets);
            }
        }
    }
}
