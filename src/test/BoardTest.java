package test;

import javasrc.*;
import java.util.List;
import static java.util.Arrays.asList;

public class BoardTest
{
    private final static Board boardTest = new SquareBoard(2);

    public static void main(String[] args)
    {
        boardTest.fillBoard(asList(1,2,3,null));
        if(boardTest.availableSpace().get(0)!=boardTest.getKey(1,1))
        {
            throw new RuntimeException("availableSpase не работает");
        }
        if(!boardTest.getKey(0,0).equals(boardTest.getKey(0,0)))
        {
            throw new RuntimeException("Ключи должны быть одинаковыми");
        }
        if(boardTest.getKey(2,2)!=null)
        {
            throw new RuntimeException("Не найденный ключ должен быть null");
        }
        if(boardTest.getValue(boardTest.getKey(1,1))!=null)
        {
            throw new RuntimeException("getValue не работает");
        }
        if( (!boardTest.hasValue(null)) || (!boardTest.hasValue(3)) )
        {
            throw new RuntimeException("hasValue не работает");
        }
        assertEquals(boardTest.getColumn(0), asList(boardTest.getKey(0,0), boardTest.getKey(1,0)));
        assertEquals(boardTest.getRow(0),    asList(boardTest.getKey(0,0), boardTest.getKey(0,1)));
        assertEquals(boardTest.getValues(boardTest.getColumn(0)), asList(1,3));
        boardTest.fillBoard(asList(5,6,7,8));
        if(!boardTest.availableSpace().isEmpty())
        {
            throw new RuntimeException("fillBoard некорректно работает");
        }
        System.out.println("BoardTest успешно завершен");
    }

    public static void assertEquals(List list1, List list2)
    {
        if(!list1.equals(list2))
        {
            throw new RuntimeException("List1:"+list1+" не совпадает с List2: "+list2);
        }
    }
}






























