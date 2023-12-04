package javasrc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;

public class Game2048 implements Game
{
    private GameHelper helper = new GameHelper();
    private Board board;
    private Random random = new Random();
    public Game2048(Board newBoard)
    {
        board = newBoard;
    }


    @Override
    public void init()
    {
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<board.weight*board.height; i++)
        {
            list.add(null);
        }
        board.fillBoard(list);
        addItem();
    }

    @Override
    public boolean canMove()
    {
        if(board.availableSpace().isEmpty())
        {
            for(int r=0; r<board.height; r++)
            {
                List<Integer> listRow=board.getValues(board.getRow(r));
                List<Integer> listRowMove=helper.moveAndMergeEqual(board.getValues(board.getRow(r)));
                if(!listRow.equals(listRowMove))
                {
                    return true;
                }
            }
            for(int c=0; c<board.weight; c++)
            {
                List<Integer> listColumn=board.getValues(board.getColumn(c));
                List<Integer> listColumnMove=helper.moveAndMergeEqual(board.getValues(board.getColumn(c)));
                if(!listColumn.equals(listColumnMove))
                {
                    return true;
                }
            }
        }
        else
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Direction direction)
    {
        List<Integer> newValues = new ArrayList<Integer>(),
                      oldValues = new ArrayList<Integer>();
        for(int i=0; i<board.height; i++)
        {
            oldValues.addAll(board.getValues(board.getRow(i)));
        }
        if((direction==Direction.UP) || (direction==Direction.DOWN))
        {
            for(int i=0; i<board.weight*board.height; i++)
            {
                newValues.add(null);
            }
            if(direction==Direction.UP)
            {
                for(int i=0; i<board.weight; i++)
                {
                    List<Integer> tmp=helper.moveAndMergeEqual(board.getValues(board.getColumn(i)));
                    for(int j=0; j<tmp.size(); j++)
                    {
                        newValues.set(i+(tmp.size()*j), tmp.get(j));
                    }
                }
            }
            if(direction==Direction.DOWN)
            {
                for(int i=0; i<board.weight; i++)
                {
                    List<Integer> tmp=helper.moveAndMergeEqual(board.getValues(board.getColumn(i)));
                    for(int k=tmp.size()-1; k>=0; k--)
                    {
                        for(int j=tmp.size()-1; j>=0; j--)
                        {
                            if(tmp.get(j)==null)
                            {
                                tmp.set(j, tmp.get(k));
                                tmp.set(k, null);
                            }
                        }
                    }
                    for(int j=0; j<tmp.size(); j++)
                    {
                        newValues.set(i+(tmp.size()*j), tmp.get(j));
                    }
                }
            }
        }
        else
        {
            if(direction==Direction.LEFT)
            {
                for(int i=0; i<board.height; i++)
                {
                    newValues.addAll(helper.moveAndMergeEqual(board.getValues(board.getRow(i))));
                }
            }
            if(direction==Direction.RIGHT)
            {
                for(int i=0; i<board.height; i++)
                {
                    List<Integer> tmp=helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
                    for(int k=tmp.size()-1; k>=0; k--)
                    {
                        for(int j=tmp.size()-1; j>=0; j--)
                        {
                            if(tmp.get(j)==null)
                            {
                                tmp.set(j, tmp.get(k));
                                tmp.set(k, null);
                            }
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
    public void addItem()
    {
        List<Key> keyList=board.availableSpace();
        if(!keyList.isEmpty())
        {
            int a=random.nextInt(keyList.size());
            if((a>=0)&&(a<keyList.size()))
            {
                board.addItem(keyList.get(a), 2);
            }
        }
    }

    @Override
    public Board getGameBoard()
    {
        return board;
    }

    @Override
    public boolean hasWin()
    {
        return board.hasValue(2048);
    }
}
