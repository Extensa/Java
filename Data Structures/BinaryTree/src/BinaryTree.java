import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> {
    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value,
           BinaryTree<T> leftChild,
           BinaryTree<T> rightChild) {
        this.setValue(value);
        this.setLeftChild(leftChild);
        this.setRightChild(rightChild);
    }

    public BinaryTree(T value) {
        this(value, null, null);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTree<T> getLeftChild() {
        return leftChild;
    }

    private void setLeftChild(BinaryTree<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTree<T> getRightChild() {
        return rightChild;
    }

    private void setRightChild(BinaryTree<T> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean add(T item) {
        if (item.equals(this.value)) {
            return false;
        }
        else if (item.compareTo(this.value) < 0) {
            if (this.leftChild == null) {
                this.leftChild = new BinaryTree<T>(item);
                return true;
            }
            else {
                this.leftChild.add(item);
            }
        }
        else if (item.compareTo(this.value) > 0) {
            if (this.rightChild == null) {
                this.rightChild = new BinaryTree<T>(item);
                return true;
            }
            else {
                this.rightChild.add(item);
            }
        }

        return false;
    }

    public String printPreOrder(int indent) {
        StringBuilder result = new StringBuilder();

        result.append(new String(new char[indent * 2]).replace("\0"," "));
        result.append(this.getValue()).append("\n");

        if (this.getLeftChild() != null) {
            result.append(this.getLeftChild().printPreOrder(indent + 1));
        }
        if (this.getRightChild() != null) {
            result.append(this.getRightChild().printPreOrder(indent + 1));
        }

        return result.toString();
    }

    //Breadth-First-Search
    public void eachBreadthFirstSearch(Consumer<T> consumer) {
        Queue<BinaryTree<T>> nodes = new ArrayDeque<>();
        nodes.add(this);

        while (nodes.size() > 0) {
            BinaryTree<T> currentNode = nodes.remove();
            consumer.accept(currentNode.getValue());

            if (currentNode.getLeftChild() != null) {
                nodes.add(currentNode.getLeftChild());
            }
            if (currentNode.getRightChild() != null) {
                nodes.add(currentNode.getRightChild());
            }
        }
    }

    //Depth-Fisrst-Search
    public void eachPreOrder(Consumer<T> consumer) {
        consumer.accept(this.value);

        if (this.leftChild != null) {
            this.leftChild.eachInOrder(consumer);
        }
        if (this.rightChild != null) {
            this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachInOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
            this.leftChild.eachInOrder(consumer);
        }

        consumer.accept(this.value);

        if (this.rightChild != null) {
            this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
            this.leftChild.eachPostOrder(consumer);
        }
        if (this.rightChild != null) {
            this.rightChild.eachPostOrder(consumer);
        }

        consumer.accept(this.value);
    }
}
