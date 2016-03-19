public class MainClass {
    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>();

        queue.enqueue("B");
        queue.enqueue("a");
        queue.enqueue("t");
        queue.enqueue("M");
        queue.enqueue("a");
        queue.enqueue("n");

        String r = "";
        for (String o : queue) {
            r += o;
        }

        System.out.println(r);

        System.out.println(queue);
    }
}
