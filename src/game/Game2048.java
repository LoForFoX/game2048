package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {
    private GameHelper helper = new GameHelper();
    private Board board;
    private Random random = new Random();
    public Game2048(Board newBoard) {
        board = newBoard;
    }


    @Override
    public void init() {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i<board.width *board.height; i++) {
            list.add(null);
        }
        board.fillBoard(list);
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
                    List<Integer> tmpColumn=board.getValues(board.getColumn(i));
                    for(int ri=0; ri<tmpColumn.size()/2; ri++){
                        Integer t=tmpColumn.get(ri);
                        tmpColumn.set(ri, tmpColumn.get(tmpColumn.size()-1-ri));
                        tmpColumn.set(tmpColumn.size()-1-ri, t);
                    }
                    List<Integer> tmp=helper.moveAndMergeEqual(tmpColumn);
                    for(int ri=0; ri<tmp.size()/2; ri++){
                        Integer t=tmp.get(ri);
                        tmp.set(ri, tmp.get(tmp.size()-1-ri));
                        tmp.set(tmp.size()-1-ri, t);
                    }
                    int counterNULL=0;
                    for(int ri=0; ri<tmp.size(); ri++){
                        if(tmp.get(ri)==null){
                            counterNULL++;
                        }
                    }
                    if(counterNULL<tmp.size()){
                        while (tmp.get(tmp.size()-1)==null){
                            tmp.remove(tmp.size()-1);
                            tmp.add(0,null);
                        }
                    }
                    for(int j=0; j<tmp.size(); j++) {
                        newValues.set(i+(tmp.size()*j), tmp.get(j));
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
                    List<Integer> tmpRow=board.getValues(board.getRow(i));
                    for(int ri=0; ri<tmpRow.size()/2; ri++){
                        Integer t=tmpRow.get(ri);
                        tmpRow.set(ri, tmpRow.get(tmpRow.size()-1-ri));
                        tmpRow.set(tmpRow.size()-1-ri, t);
                    }
                    List<Integer> tmp=helper.moveAndMergeEqual(tmpRow);
                    for(int ri=0; ri<tmp.size()/2; ri++){
                        Integer t=tmp.get(ri);
                        tmp.set(ri, tmp.get(tmp.size()-1-ri));
                        tmp.set(tmp.size()-1-ri, t);
                    }
                    int counterNULL=0;
                    for(int ri=0; ri<tmp.size(); ri++){
                        if(tmp.get(ri)==null){
                            counterNULL++;
                        }
                    }
                    if(counterNULL<tmp.size()){
                        while (tmp.get(tmp.size()-1)==null){
                            tmp.remove(tmp.size()-1);
                            tmp.add(0,null);
                        }
                    }
                    newValues.addAll(tmp);
                }
            }
        }
        board.fillBoard(newValues);
        return !newValues.equals(oldValues);
    }

    @Override
    public void addItem() {
        List<Key> keyList=board.availableSpace();
        if(!keyList.isEmpty()) {
            int a=random.nextInt(keyList.size());
            if((a>=0)&&(a<keyList.size())) {
                board.addItem(keyList.get(a), 2);
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
