package painter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomPanel extends JPanel{
    private int size=100, count=4;
    private String values[][];
    private int typeMessage=0;

    public CustomPanel(int countElement){
        count=countElement;
        size=(400-10*(count-1))/countElement;
        values = new String[count][count];
        for(int i=0; i<count; i++){
            for(int j=0; j<count; j++){
                values[i][j]="";
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setBackground(new Color(230,230,230));
        if(typeMessage==0){
            for(int i=0; i<count; i++){
                for(int j=0; j<count; j++){
                    g2.setColor(Color.lightGray);
                    g2.fillRect(10*(1+i)+size*i,10*(j+1)+size*j,size,size);
                    g2.setColor(Color.white);
                    g2.fillRect(10*(1+i)+size*i+1,10*(j+1)+size*j+1,size-2,size-2);

                    g2.setColor(Color.black);
                    g2.setFont(new Font("Times", Font.BOLD, size/2));
                    g2.drawString(values[i][j], 10*(1+i)+size*i+40-values[i][j].length()*10,10*(j+1)+size*j+60);
                }
            }
        }
        else{
            g2.setFont(new Font("Times", Font.BOLD, 30));
            g2.setColor(Color.orange);
            String strMessage="";
            switch (typeMessage){
                case 1:{
                    g2.setColor(Color.green);
                    strMessage="ПОБЕДА";
                }break;
                case 2:{
                    g2.setColor(Color.red);
                    strMessage="ПОРАЖЕНИЕ";
                }break;
            }
            g2.drawString(strMessage, 250-strMessage.length()*12, 100);
            g2.setColor(Color.gray);
            strMessage="ДЕЙСТВИЯ:";
            g2.drawString(strMessage, 250-strMessage.length()*10, 200);
            strMessage="НОВАЯ ИГРА - КЛАВИША \"N\"";
            g2.drawString(strMessage, 250-strMessage.length()*10, 250);
            strMessage="ВЫХОД - КЛАВИША \"X\"";
            g2.drawString(strMessage, 250-strMessage.length()*10, 300);
        }
    }
    public void setValues(int row, List<Integer>listValues){
        for(int i=0; i<listValues.size(); i++){
            String str=""+listValues.get(i);
            str=str.replace("null", "");
            values[i][row]=str;
        }
    }
    public void setMessage(int type){
        typeMessage=type;
    }

}