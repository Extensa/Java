import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class BinaryTreeTest {
    private BinaryTree<Integer> binaryTree;
    private List<Integer> returned;

    @Before
    public void initMethod() {
        this.binaryTree = new BinaryTree<>(1,
                            new BinaryTree<>(2,
                                    new BinaryTree<>(3,
                                            new BinaryTree<>(4),
                                            new BinaryTree<>(5)),
                                    new BinaryTree<>(6,
                                            new BinaryTree<>(7),
                                            new BinaryTree<>(8))),
                            new BinaryTree<>(9));

        this.returned = new ArrayList<>();
    }

    @Test
    public void add_ElementEqualToTheRoot() {
        boolean result = this.binaryTree.add(1);

        assertEquals(false, result);
    }

    @Test
    public void add_ElementBiggerThanAnyOther_InTheTree() {
        this.binaryTree.add(10);
        this.binaryTree.eachBreadthFirstSearch(this.returned::add);

        Integer[] expected = new Integer[] { 1, 2, 9, 3, 6, 10, 4, 5, 7, 8 };

        assertArrayEquals(expected, this.returned.toArray());
    }

    @Test
    public void printPreOrder_test() {
        String expected = "1\n  2\n    3\n      4\n      5\n    6\n      7\n      8\n  9\n";
        String returned = this.binaryTree.printPreOrder(0);

        assertEquals(expected,returned);
    }

    @Test
    public void eachBreadthFirstSearch_test() {
        this.binaryTree.eachBreadthFirstSearch(this.returned::add);

        Integer[] expected = new Integer[] { 1, 2, 9, 3, 6, 4, 5, 7, 8 };

        assertArrayEquals(expected, this.returned.toArray());
    }
    
    @Test
    public void eachInOrder_test() {
        this.binaryTree.eachInOrder(this.returned::add);

        Integer[] expected = new Integer[] { 4, 3, 5, 2, 7, 6, 8, 1, 9 };

        assertArrayEquals(expected, this.returned.toArray());
    }

    @Test
    public void eachPostOrder_test() {
        this.binaryTree.eachPostOrder(this.returned::add);

        Integer[] expected = new Integer[] { 4, 5, 3, 7, 8, 6, 2, 9, 1 };

        assertArrayEquals(expected, this.returned.toArray());
    }

    @Test
    public void eachPreOrder_test() {
        this.binaryTree.eachPreOrder(this.returned::add);

        Integer[] expected = new Integer[] { 1, 4, 3, 5, 2, 7, 6, 8, 9};

        assertArrayEquals(expected, this.returned.toArray());
    }
}