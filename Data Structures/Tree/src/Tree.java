import java.util.*;
import java.util.function.Consumer;

public class Tree<E> {
    private E value;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... children) {
        this.setValue(value);
        this.children = new ArrayList<>();

        Collections.addAll(this.children, children);
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        if (value == null) {
            throw new IllegalArgumentException("The value cannot be null.");
        }

        this.value = value;
    }

    public E breadthFirstSearch(E item) {
        E result = null;
        Queue<Tree<E>> nodes = new ArrayDeque<>();

        nodes.add(this);
        while (nodes.size() > 0) {
            Tree<E> currentNode = nodes.remove();

            if (currentNode.getValue().equals(item)) {
                result = currentNode.getValue();
                break;
            }

            for (Tree<E> child : currentNode.getChildren()) {
                nodes.add(child);
            }
        }

        return result;
    }

    public E depthFirstSearch(E item) {
        E result = null;
        Stack<Tree<E>> nodes = new Stack<>();

        nodes.push(this);
        while (nodes.size() > 0) {
            Tree<E> currentNode = nodes.pop();

            if (currentNode.getValue().equals(item)) {
                result = currentNode.getValue();
                break;
            }

            for (Tree<E> child : currentNode.getChildren()) {
                nodes.push(child);
            }
        }

        return result;
    }

    public List<Tree<E>> getChildren() {
        return children;
    }

    public String print(int indent) {
        StringBuilder result = new StringBuilder();
        result.append(new String(new char[indent * 2]).replace("\0", "-"));
        result.append(this.getValue()).append("\n");

        for (Tree<E> child : this.getChildren()) {
            result.append(child.print(indent + 1));
        }

        return result.toString();
    }

    public void each(Consumer<E> consumer) {
        consumer.accept(this.getValue());

        for (Tree<E> child: this.getChildren()){
            child.each(consumer);
        }
    }
}
