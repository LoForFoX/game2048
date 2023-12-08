package painter;

import game.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartGame {
    private int size=4;
    private CustomPanel panel = new CustomPanel(size);
    private Board board = new SquareBoard(size);
    private Game game2048 = new Game2048(board);
    private boolean stopGame=false;

    public static void main(String[] args){
        StartGame sg = new StartGame();
        sg.createWindow();
        sg.updateWindow();
    }

    private void createWindow(){
        game2048.init();
        JFrame frame = new JFrame();
        Container container = frame.getContentPane();
        container.add(panel);
        panel.repaint();
        int windowSize=500;
        frame.setSize(windowSize,windowSize);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent event){
                if((event.getKeyCode()==KeyEvent.VK_W) || (event.getKeyCode()==KeyEvent.VK_UP)){
                    setMove(Direction.UP);
                }
                if((event.getKeyCode()==KeyEvent.VK_S) || (event.getKeyCode()==KeyEvent.VK_DOWN)){
                    setMove(Direction.DOWN);
                }
                if((event.getKeyCode()==KeyEvent.VK_A) || (event.getKeyCode()==KeyEvent.VK_LEFT)){
                    setMove(Direction.LEFT);
                }
                if((event.getKeyCode()==KeyEvent.VK_D) || (event.getKeyCode()==KeyEvent.VK_RIGHT)){
                    setMove(Direction.RIGHT);
                }
                if(stopGame){
                    if((event.getKeyCode()==KeyEvent.VK_N)){
                        game2048.init();
                        panel.setMessage(0);
                        updateWindow();
                    }
                    if((event.getKeyCode()==KeyEvent.VK_X)){
                        System.exit(0);
                    }
                }
            }
        });
    }

    private void updateWindow(){
        for(int i=0; i<size; i++){
            panel.setValues(i, board.getValues(board.getRow(i)));
        }
        panel.repaint();
    }
    private void setMove(Direction direction){
        if(game2048.move(direction)){
            game2048.addItem();
        }
        if((!game2048.canMove()) && (game2048.getGameBoard().availableSpace().isEmpty())){
            panel.setMessage(2);
            stopGame=true;
        }
        if(game2048.hasWin()){
            panel.setMessage(1);
            stopGame=true;
        }
        updateWindow();
    }
}
