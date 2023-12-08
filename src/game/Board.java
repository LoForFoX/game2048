package game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board<K,V>{
    int width;
    int height;
    Map<K, V> board = new HashMap<>();

    public Board(int width, int height) {
        this.width =width;
        this.height=height;
    }
    public abstract void fillBoard(List<V> list);
    public abstract List<K> availableSpace();
    public abstract void addItem(K key, V value);
    public abstract K getKey(int i, int j);
    public abstract V getValue(K key);
    public abstract List<K> getColumn(int i);
    public abstract List<K> getRow(int i);
    public abstract boolean hasValue(V value);
    public abstract List<V> getValues(List<K> keys);
}
