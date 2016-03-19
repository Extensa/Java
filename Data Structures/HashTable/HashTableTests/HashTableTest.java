import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashTableTest {
    private HashTable<String, Integer> table;

    @Before
    public void initMethod() {
        this.table = new HashTable<>();
        this.table.add("C#", 10);
        this.table.add("Java", 5);
        this.table.add("PHP" , 1);
    }

    @Test
    public void add_EmptyHashTable_NoDuplicates() {
        HashTable<String, Integer> localTable = new HashTable<>();

        localTable.add("A", 1);
        localTable.add("B", 2);
        localTable.add("C", 3);
        localTable.add("D", 4);

        assertEquals(4, localTable.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_EmptyHashTable_Duplicates() {
        HashTable<String, Integer> localTable = new HashTable<>();

        table.add("A", 1);
        table.add("A", 2);
    }

    @Test
    public void add_PopulatedHashTable_NoDuplicates() {
        this.table.add("JS", 8);
        this.table.add("HTML", 28);

        assertEquals(5, this.table.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_PopulatedHashTable_Duplicates() {
        this.table.add("Java", 101);
    }

    @Test
    public void add_500_elements_test_growIfNeeded() {
        HashTable<String, Integer> localTable = new HashTable<>(1);

        for (int i = 0; i < 500; i++) {
            localTable.add("key" + i, i);
        }

        assertEquals(500, localTable.getCount());
    }

    @Test
    public void addOrReplace_Duplicates() {
        this.table.addOrReplace("Java", 101);

        ArrayList<KeyValue<String, Integer>> list = this.table.toList();

        int expectedValueOfKeyJava = 101;
        int actual = 0;

        for (KeyValue<String, Integer> keyValue : list) {
            if (keyValue.getKey().equals("Java")) {
                actual = keyValue.getValue();
            }
        }

        assertEquals(expectedValueOfKeyJava, actual);
    }

    @Test
    public void remove_Existing_Element() {
        boolean isRemoved = this.table.remove("Java");

        assertTrue(isRemoved);
        assertEquals(2, this.table.getCount());
    }

    @Test
    public void remove_NonExisting_Element() {
        boolean isRemoved = this.table.remove("C++");

        assertEquals(false, isRemoved);
        assertEquals(3, this.table.getCount());
    }

    @Test
    public void get_Existing_Element() {
        int expectedValue = 1;
        int actual = this.table.get("PHP");

        assertEquals(expectedValue, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void get_NonExisting_Element() {
        this.table.get("Batman");
    }

    @Test
    public void contains_Existing_Element() {
        boolean hasFound = this.table.contains("PHP");

        assertTrue(hasFound);
    }

    @Test
    public void contains_NonExisting_Element() {
        boolean hasFound = this.table.contains("C++");

        assertEquals(false, hasFound);
    }
}
