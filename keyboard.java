import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
public class keyboard extends KeyAdapter
{
    display screen;
    public keyboard(display s,int X,int Y)
    {
        screen = s;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W)
            screen.setW(true);
        if(key == KeyEvent.VK_A)
            screen.setA(true);
        if(key == KeyEvent.VK_S)
            screen.setS(true);
        if(key == KeyEvent.VK_D)
            screen.setD(true);
        if(key == KeyEvent.VK_E)
        {
            if(screen.E == false)
                screen.view = "inventory";
            else
                screen.view = "world";
            System.out.println(screen.view);
        }
        if(key == KeyEvent.VK_Q)
        {
            if(screen.Q == false)
                screen.view = "crafting";
            else
                screen.view = "world";
        }
        if(key == KeyEvent.VK_1)
            screen.tools.toolSelected = 0;
        if(key == KeyEvent.VK_2)
            screen.tools.toolSelected = 1;
        if(key == KeyEvent.VK_3)
            screen.tools.toolSelected = 2;
        if(key == KeyEvent.VK_4)
            screen.tools.toolSelected = 3;
        if(key == KeyEvent.VK_5)
            screen.tools.toolSelected = 4;
        if(key == KeyEvent.VK_6)
            screen.tools.toolSelected = 5;
        if(key == KeyEvent.VK_7)
            screen.tools.toolSelected = 6;
        if(key == KeyEvent.VK_8)
            screen.tools.toolSelected = 7;
        if(key == KeyEvent.VK_9)
            screen.tools.toolSelected = 8;
        if(key == KeyEvent.VK_0)
            screen.tools.toolSelected = 9;
        //if(key == KeyEvent.VK_SPACE)
        //    screen.setSpace(true);
        screen.drawing();
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W)
            screen.setW(false);
        if(key == KeyEvent.VK_A)
            screen.setA(false);
        if(key == KeyEvent.VK_S)
            screen.setS(false);
        if(key == KeyEvent.VK_D)
            screen.setD(false);
        if(key == KeyEvent.VK_SPACE)
            screen.setSpace(false);
        screen.drawing();
    }
    // System.out.println("switching to inventory");
                // for(Button b: screen.Buttons)
                // {
                    // if(b.isTouchingMouse(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y) == true)
                        // screen.view = "inventory";
                // }
}