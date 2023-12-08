package game;

import java.util.ArrayList;
import java.util.List;

public class SquareBoard<V> extends Board<Key, V>{
    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<V> list) {
        board.clear();
        int currentItem=0;
        for(int row=0; row<height; row++) {
            for(int column = 0; column< width; column++) {
                Key keyItem = new Key(row, column);
                if(currentItem<list.size()) {
                    board.put(keyItem, list.get(currentItem));
                }
                else {
                    board.put(keyItem, null);
                }
                currentItem++;
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> nullList = new ArrayList<>();
        for(Key k: board.keySet()) {
            if(board.get(k)==null) {
                nullList.add(k);
            }
        }
        return nullList;
    }

    @Override
    public void addItem(Key key, V value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for(Key k: board.keySet()) {
            if(k.equals(i,j)) {
                return k;
            }
        }
        return null;
    }

    @Override
    public V getValue(Key k) {
        return board.get(k);
    }

    @Override
    public List<Key> getColumn(int column) {
        List<Key> listKey = new ArrayList<Key>();
        for(Key k: board.keySet()) {
            if(k.getJ()==column) {
                listKey.add(k);
            }
        }
        for (int i=0; i<listKey.size(); i++) {
            for (int j=0; j<listKey.size(); j++) {
                if(listKey.get(i).getI()<listKey.get(j).getI()) {
                    Key tmp=listKey.get(i);
                    listKey.set(i, listKey.get(j));
                    listKey.set(j, tmp);
                }
            }
        }
        return listKey;
    }

    @Override
    public List<Key> getRow(int row) {
        List<Key> listKey = new ArrayList<Key>();
        for(Key k: board.keySet()) {
            if(k.getI()==row) {
                listKey.add(k);
            }
        }
        for (int i=0; i<listKey.size(); i++) {
            for (int j=0; j<listKey.size(); j++) {
                if(listKey.get(i).getJ()<listKey.get(j).getJ()) {
                    Key tmp=listKey.get(i);
                    listKey.set(i, listKey.get(j));
                    listKey.set(j, tmp);
                }
            }
        }
        return listKey;
    }

    @Override
    public boolean hasValue(V value) {
        return board.containsValue(value);
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> listValue = new ArrayList<>();
        for (Key currentKey : keys) {
            if (board.containsKey(currentKey)) {
                listValue.add(board.get(currentKey));
            }
        }
        return listValue;
    }
}
