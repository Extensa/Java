public class KeyValue<TKey, TValue>{
    private TKey key;
    private TValue value;

    public KeyValue(TKey key, TValue value) {
        this.setKey(key);
        this.setValue(value);
    }

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    @Override
    public int hashCode()
    {
        return this.combineHashCodes(this.key.hashCode(), this.value.hashCode());
    }

    private int combineHashCodes(int h1, int h2)
    {
        return ((h1 << 5) + h1) ^ h2;
    }

    @Override
    public String toString()
    {
        return String.format("[%s -> %s]", this.key, this.value);
    }
}
