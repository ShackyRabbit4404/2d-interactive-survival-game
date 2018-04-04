import java.awt.event.*;
import java.util.*;
import java.awt.*;
public class mouse implements MouseListener
{
    display screen;
    ArrayList<Button> buttons;
    public mouse(display s,ArrayList<Button> b)
    {
        screen = s;
        buttons = b;
    }

    public void mousePressed(MouseEvent e) {
        if(screen.view.equals("world"))
        {
            screen.space = true;
            screen.hit();
        }
        else if(screen.view.equals("crafting"))
        {
            for(Button b: buttons)
            {
                if(b.visible == true && b.clickable == true)
                {
                    Rectangle mousePointer = new Rectangle(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y,1,1);
                    if(mousePointer.intersects(new Rectangle(b.X,b.Y + 40,b.XLength,b.YLength)))
                    {
                        if(b.name.equals("wooden axe") && screen.currH.getNumItem("wood") >= 10)
                        {
                            screen.currH.inven.add(new item("wooden axe tool",1));
                            screen.currH.addItems("wood",-10);
                        }
                        else if(b.name.equals("stone axe") && screen.currH.getNumItem("wood") >= 20 && screen.currH.getNumItem("stone") >= 10)
                        {
                            screen.currH.inven.add(new item("stone axe tool",1));
                            screen.currH.addItems("wood",-20);
                            screen.currH.addItems("stone",-10);
                        }
                    }
                }
            }
        }
        else if(screen.view.equals("inventory"))
        {
            for(Button b: screen.inventoryItems)
            {
                if(b.visible == true)
                {
                    Rectangle mousePointer = new Rectangle(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y,1,1);
                    if(mousePointer.intersects(new Rectangle(b.X,b.Y + 40,b.XLength,b.YLength)))
                    {
                        screen.tools.toolNames[screen.tools.toolSelected] = b.name; 
                        System.out.print(b.name);
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }
}