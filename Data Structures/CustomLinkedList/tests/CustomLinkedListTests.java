import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomLinkedListTests {
    private CustomLinkedList<Integer> list;
    private CustomLinkedList<Integer> emptyList;

    @Before
    public void listInit() {
        this.emptyList = new CustomLinkedList<>();

        this.list = new CustomLinkedList<>();
        this.list.addLast(10);
        this.list.addLast(20);
        this.list.addLast(30);
        this.list.addLast(40);
    }

    @Test
    public void addLast_InEmptyList() {
        Integer expected = 20;
        this.emptyList.addLast(expected);

        assertEquals(expected, this.emptyList.getFirst());
        assertEquals(expected, this.emptyList.getLast());
    }

    @Test
    public void addFirst_InEmptyList() {
        Integer expected = 10;
        this.emptyList.addFirst(expected);

        assertEquals(expected, this.emptyList.getFirst());
        assertEquals(expected, this.emptyList.getLast());
    }

    @Test(expected = IllegalStateException.class)
    public void removeFirst_InEmptyList() {
        this.emptyList.removeFirst();
    }

    @Test(expected = IllegalStateException.class)
    public void removeLast_InEmptyList() {
        this.emptyList.removeLast();
    }

    @Test
    public void addLast_InPopulatedList() {
        Integer expected = 50;
        this.list.addLast(expected);

        assertNotEquals(expected, this.list.getFirst());
        assertEquals(expected, this.list.getLast());
    }

    @Test
    public void addFirst_InPopulatedList() {
        Integer expected = 404;
        this.list.addFirst(expected);

        assertNotEquals(expected, this.list.getLast());
        assertEquals(expected, this.list.getFirst());
    }

    @Test
    public void removeFirst_InPopulatedList() {
        Integer expected = 10;
        Integer actual = this.list.removeFirst();

        assertEquals(expected, actual);
        assertEquals(new Integer(20), this.list.getFirst());
    }

    @Test
    public void removeLast_InPopulatedList() {
        Integer expected = 40;
        Integer actual = this.list.removeLast();

        assertEquals(expected, actual);
        assertEquals(new Integer(30), this.list.getLast());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_InvalidIndex() {
        this.list.get(-1);
    }

    @Test(expected = IllegalStateException.class)
    public void get_FromEmptyList() {
        this.emptyList.get(1);
    }

    @Test
    public void get_FromPopulatedList() {
        Integer expected = 20;
        Integer actual = this.list.get(1);

        assertEquals(expected, actual);
    }
}
