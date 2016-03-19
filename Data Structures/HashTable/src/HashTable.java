import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class HashTable<TKey, TValue>{
    private static final int INITIAL_CAPACITY = 16;

    private LinkedList<KeyValue<TKey, TValue>>[] slots;
    private int count;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    public HashTable(int capacity) {
        this.setSlots(new LinkedList[capacity]);
        this.setCount(0);
    }

    public LinkedList<KeyValue<TKey, TValue>>[] getSlots() {
        return slots;
    }

    private void setSlots(LinkedList<KeyValue<TKey, TValue>>[] slots) {
        this.slots = slots;
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public int getCapacity() {
        return this.slots.length;
    }

    private void growIfNeeded() {
        if ((float)(this.count + 1) / this.getCapacity() > 0.75) {
            HashTable<TKey, TValue> newHashTable = new HashTable<>(2 * this.getCapacity());

            for (LinkedList<KeyValue<TKey, TValue>> elements : this.slots) {
                if (elements != null) {
                    for (KeyValue<TKey, TValue> element : elements) {
                        newHashTable.add(element.getKey(), element.getValue());
                    }
                }
            }

            this.setSlots(newHashTable.getSlots());
            this.setCount(newHashTable.getCount());
        }
    }

    private int findSlotNumber(TKey key) {
        return Math.abs(key.hashCode()) % this.getCapacity();
    }

    public void add(TKey key, TValue value) {
        this.growIfNeeded();

        int slotNumber = this.findSlotNumber(key);
        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<TKey, TValue> element : this.slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists");
            }
        }

        KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(newElement);
        this.count++;
    }

    public boolean addOrReplace(TKey key, TValue value) {
        this.growIfNeeded();

        int slotNumber = this.findSlotNumber(key);
        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList();
        }

        for (KeyValue<TKey, TValue> element : this.slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                element.setValue(value);
                return false;
            }
        }

        KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(newElement);
        this.count++;
        return true;
    }

    public boolean remove(TKey key) {
        int slotNumber = this.findSlotNumber(key);
        LinkedList<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];

        if (elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if (element.getKey().equals(key)) {
                    elements.remove(element);
                    this.count--;
                    return true;
                }
            }
        }

        return false;
    }

    public void clear() {
        this.setSlots(new LinkedList[INITIAL_CAPACITY]);
        this.setCount(0);
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int slotNumber = this.findSlotNumber(key);
        LinkedList<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];

        if (elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if (element.getKey().equals(key)) {
                    return element;
                }
            }
        }

        return null;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> element = this.find(key);
        if (element == null) {
            throw new NoSuchElementException("The element was not found!");
        }

        return element.getValue();
    }

    public boolean contains(TKey key) {
        KeyValue<TKey, TValue> element = this.find(key);
        return element != null;
    }

    public ArrayList<KeyValue<TKey, TValue>> toList() {
        ArrayList<KeyValue<TKey, TValue>> list = new ArrayList<>();

        for (LinkedList<KeyValue<TKey, TValue>> elements : slots) {
            if (elements != null) {
                list.addAll(elements.stream()
                        .filter(element -> element != null)
                        .collect(Collectors.toList()));
            }
        }

        return list;
    }
}
