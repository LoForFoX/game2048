package test;

import javasrc.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
public class Test2048
{
    public static void main(String[] args)
    {
        Test2048 test = new Test2048();
        int size=4;
        Board board = new SquareBoard(size);
        Game game2048 = new Game2048(board);
        List<Integer> list=   asList(null, 4,    null, null,null, 4,    null, null,2,    8, 4,    null, 4,    4,    8,    4),
                    listUP=   asList(2,    8,    4,    4,   4,    8,    8,    null,null, 4, null, null, null, null, null, null),
                    listDOWN= asList(null, null, null, null,null, null, null, null,2,    16,4,    null, 4,    4,    8,    4),
                    listRIGHT=asList(null, null, null, null,null, null, null, null,null, 2, 16,   4,    null, 8,    8,    4),
                    listLEFT= asList(null, null, null, null,null, null, null, null,2,    16,4,    null, 16,   4,    null, null);
        System.out.println("Тестовая таблица:");
        board.fillBoard(list);
        test.printBoard(board, size);
        System.out.println("\nПроверка move");
        for(int k=0; k<4; k++)
        {
            List<Integer> listEtalon = new ArrayList<Integer>();
            Direction directionCheck = null;
            if(k==0)
            {
                directionCheck=Direction.UP;
                listEtalon = listUP;
            }
            if(k==1)
            {
                directionCheck=Direction.DOWN;
                listEtalon = listDOWN;
            }
            if(k==2)
            {
                directionCheck=Direction.RIGHT;
                listEtalon = listRIGHT;
            }
            if(k==3)
            {
                directionCheck=Direction.LEFT;
                listEtalon = listLEFT;
            }
            game2048.move(directionCheck);
            List<Integer> tmp = new ArrayList<Integer>();
            for(int i=0; i<size; i++)
            {
                tmp.addAll(board.getValues(board.getRow(i)));
            }
            test.assertEquals(listEtalon, tmp);
            System.out.println("Проверка : "+directionCheck.toString()+" - успешно");
        }

        int countNull=board.availableSpace().size();
        game2048.addItem();
        if(countNull<=board.availableSpace().size())
        {
            throw new RuntimeException("addItem не работает");
        }
        else
        {
            System.out.println("\nПроверка addItem - успешно");
        }

        List<Integer> fullList1=asList(8, 4, 2, 8, 4, 4, 2, 4, 2, 8, 4, 2, 4, 4, 8, 4),
                      fullList2=asList(8, 4, 2, 8, 4, 8, 4, 2, 2, 4, 8, 4, 4, 8, 2, 8);
        board.fillBoard(fullList1);
        if(!game2048.canMove())
        {
            throw new RuntimeException("canMove не работает");
        }
        else
        {
            board.fillBoard(fullList2);
            if(game2048.canMove())
            {
                throw new RuntimeException("canMove не работает");
            }
            else
            {
                System.out.println("\nПроверка canMove - успешно");
            }
        }

        System.out.println("\nЗавершено");
    }

    private void printBoard(Board board, int size)
    {
        for (int i = 0; i < size; i++)
        {
            System.out.println(board.getValues(board.getRow(i)).toString().replace("null"," "));
        }
    }

    private void assertEquals(List list1, List list2)
    {
        if(!list1.equals(list2))
        {
            throw new RuntimeException("List1:"+list1+" не совпадает с List2: "+list2);
        }
    }
}