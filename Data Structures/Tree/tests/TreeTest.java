import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class TreeTest {

    private Tree<Integer> tree;

    @Test
    public void method_getValue() {
        Tree<Double> tree = new Tree<>(25.08);

        assertEquals(new Double(25.08), tree.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiate_tree_withNullValue() {
        Tree<Double> tree = new Tree<>(null);
    }

    @Test
    public void print_test() {
        Tree<Integer> tree = new Tree<>(5,
                new Tree<>(12),
                new Tree<>(15),
                new Tree<>(20,
                        new Tree<>(100),
                        new Tree<>(150)));


        String expected =
                "5\n" +
                "--12\n" +
                "--15\n" +
                "--20\n" +
                "----100\n" +
                "----150\n";

        String returned = tree.print(0);

        assertEquals(expected, returned);
    }

    @Before
    public void initMethod() {
        this.tree = new Tree<>(1,
                new Tree<>(2),
                new Tree<>(3),
                new Tree<>(4,
                        new Tree<>(5),
                        new Tree<>(6),
                        new Tree<>(7),
                        new Tree<>(8,
                                new Tree<>(9),
                                new Tree<>(10))));
    }

    @Test
    public void depthFirstSearch_PopulatedTree_With_10_Nodes() {
        int expectedValue = 10;
        int actual = this.tree.depthFirstSearch(expectedValue);

        assertEquals(expectedValue, actual);
    }

    @Test
    public void breadthFirstSearch_PopulatedTree_With_10_Nodes() {
        int expectedValue = 8;
        int actual = this.tree.breadthFirstSearch(expectedValue);

        assertEquals(expectedValue, actual);
    }
}