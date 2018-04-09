import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Survive");
        frame.setVisible(true);
        frame.setSize(1920,1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hero CurrentUser = new hero();
        ArrayList<Button> Buttons = new ArrayList<Button>();
        Buttons.add(new Button(1800,20,50,30,"pause",true,true));
        Buttons.add(new Button(150,250,250,75,"wooden axe",false,true));
        Buttons.add(new Button(150,350,250,75,"stone axe",false,true));
        Buttons.add(new Button(425,250,250,70,"thatch hut",false,true));
        display window = new display(CurrentUser,Buttons);
        frame.add(window);
        window.setView("world");
        ArrayList<element> worldParts = new ArrayList<element>();
        int rand = (int)(Math.random() * 200);
        int randX;
        int randY;
        //gen trees
        for(int i = 0; i < rand; i++)
        {
            randX = (int)(Math.random()*4900);
            randY = (int)(Math.random()*4900);
            worldParts.add(new element(randX,randY,100,100,("tree"),21,105,0,100));
        }
        rand = (int)(Math.random()*100);
        for(int i = 0; i < rand; i++)
        {
            randX = (int)(Math.random()*2900);
            randY = (int)(Math.random()*2900);
            worldParts.add(new element(randX,randY,100,100,("rock"),122,122,122,200));
        }
        worldParts.add(new element(0,0,3000,1,"wall",0,0,0,100));
        worldParts.add(new element(0,3000,3000,1,"wall",0,0,0,100));
        worldParts.add(new element(0,0,1,3000,"wall",0,0,0,100));
        worldParts.add(new element(3000,0,1,3000,"wall",0,0,0,100));
        window.setElements(worldParts);
        int currentX = 0;
        int currentY = 0;
        keyboard listener = new keyboard(window,currentX,currentY);
        frame.addKeyListener(listener);
        mouse mouseListener = new mouse(window,Buttons);
        frame.addMouseListener(mouseListener);
    }
}