package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    private Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    private GameHelper helper = new GameHelper();
    private Random random = new Random();
    public Game2048(Board<Key, Integer> newBoard) {
        this.board = newBoard;
    }


    @Override
    public void init() {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i<board.width *board.height; i++) {
            list.add(null);
        }
        board.fillBoard(list);
        addItem();
        addItem();
    }

    @Override
    public boolean canMove() {
        if(board.availableSpace().isEmpty()) {
            for(int r=0; r<board.height; r++) {
                List<Integer> listRow=board.getValues(board.getRow(r));
                List<Integer> listRowMove=helper.moveAndMergeEqual(board.getValues(board.getRow(r)));
                if(!listRow.equals(listRowMove)) {
                    return true;
                }
            }
            for(int c = 0; c<board.width; c++) {
                List<Integer> listColumn=board.getValues(board.getColumn(c));
                List<Integer> listColumnMove=helper.moveAndMergeEqual(board.getValues(board.getColumn(c)));
                if(!listColumn.equals(listColumnMove)) {
                    return true;
                }
            }
        }
        else {
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        List<Integer> newValues = new ArrayList<Integer>(),
                      oldValues = new ArrayList<Integer>();
        for(int i=0; i<board.height; i++) {
            oldValues.addAll(board.getValues(board.getRow(i)));
        }
        if((direction==Direction.UP) || (direction==Direction.DOWN)) {
            for(int i = 0; i<board.width *board.height; i++) {
                newValues.add(null);
            }
            if(direction==Direction.UP) {
                for(int i = 0; i<board.width; i++) {
                    List<Integer> tmp=helper.moveAndMergeEqual(board.getValues(board.getColumn(i)));
                    for(int j=0; j<tmp.size(); j++) {
                        newValues.set(i+(tmp.size()*j), tmp.get(j));
                    }
                }
            }
            if(direction==Direction.DOWN) {
                for(int i = 0; i<board.width; i++) {
                    List<Integer> tmp=swapList(helper.moveAndMergeEqual(swapList(board.getValues(board.getColumn(i)))));
                    List<Integer> swapTmp=swapNullList(tmp);
                    for(int j=0; j<swapTmp.size(); j++) {
                        newValues.set(i+(swapTmp.size()*j), swapTmp.get(j));
                    }
                }
            }
        }
        else {
            if(direction==Direction.LEFT) {
                for(int i=0; i<board.height; i++) {
                    newValues.addAll(helper.moveAndMergeEqual(board.getValues(board.getRow(i))));
                }
            }
            if(direction==Direction.RIGHT) {
                for(int i=0; i<board.height; i++) {
                    List<Integer> tmp=swapList(helper.moveAndMergeEqual(swapList(board.getValues(board.getRow(i)))));
                    newValues.addAll(swapNullList(tmp));
                }
            }
        }
        board.fillBoard(newValues);
        return !newValues.equals(oldValues);
    }

    private List<Integer> swapList(List<Integer> list){
        for(int ri=0; ri<list.size()/2; ri++){
            Integer t=list.get(ri);
            list.set(ri, list.get(list.size()-1-ri));
            list.set(list.size()-1-ri, t);
        }
        return list;
    }
    private List<Integer> swapNullList(List<Integer> list){
        int counterNULL=0;
        for (Integer itemList : list) {
            if (itemList == null) {
                counterNULL++;
            }
        }
        if(counterNULL<list.size()){
            while (list.get(list.size()-1)==null){
                list.remove(list.size()-1);
                list.add(0,null);
            }
        }
        return list;
    }


    @Override
    public void addItem() {
        List<Key> keyList=board.availableSpace();
        if(!keyList.isEmpty()) {
            int a=random.nextInt(keyList.size());
            if((a>=0)&&(a<keyList.size())) {
                board.addItem(keyList.get(a), (random.nextFloat()>0.9)?(4):(2));
            }
        }
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
