package test;

import javasrc.*;

public class TestClass
{
    public static void main(String[] args)
    {
        System.out.println("Запуск 2048");
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048(board);
        System.out.println(game2048.canMove());
        System.out.println("Завершено");
    }
}