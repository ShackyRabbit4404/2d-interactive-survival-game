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
            if(screen.tools.toolNames[screen.tools.toolSelected].equals("wooden axe tool") || screen.tools.toolNames[screen.tools.toolSelected].equals("stone axe tool")|| screen.tools.toolNames[screen.tools.toolSelected].equals("fist"))
                screen.hit();
            else
            {
                // if(MouseInfo.getPointerInfo().getLocation().x % 30 < 15 && MouseInfo.getPointerInfo().getLocation().y % 30 < 15)
                    // screen.elements.add(new element((screen.currX  + MouseInfo.getPointerInfo().getLocation().x) - ((screen.currX  + MouseInfo.getPointerInfo().getLocation().x) % 30) ,(screen.currY  + MouseInfo.getPointerInfo().getLocation().y) - ((screen.currY  + MouseInfo.getPointerInfo().getLocation().y) % 30),30,30,screen.tools.toolNames[screen.tools.toolSelected],139,69,19,100));
                // else if(MouseInfo.getPointerInfo().getLocation().x % 30 < 15 && MouseInfo.getPointerInfo().getLocation().y % 30 >= 15)
                    // screen.elements.add(new element((screen.currX  + MouseInfo.getPointerInfo().getLocation().x) - ((screen.currX  + MouseInfo.getPointerInfo().getLocation().x) % 30) ,(screen.currY  + MouseInfo.getPointerInfo().getLocation().y) + ((30 - (screen.currY  + MouseInfo.getPointerInfo().getLocation().y) % 30)),30,30,screen.tools.toolNames[screen.tools.toolSelected],139,69,19,100));
                // else if(MouseInfo.getPointerInfo().getLocation().x % 30 >= 15 && MouseInfo.getPointerInfo().getLocation().y % 30 < 15)
                    // screen.elements.add(new element((screen.currX  + MouseInfo.getPointerInfo().getLocation().x) + ((30 -(screen.currX  + MouseInfo.getPointerInfo().getLocation().x) % 30)) ,(screen.currY  + MouseInfo.getPointerInfo().getLocation().y) - ( (screen.currY  + MouseInfo.getPointerInfo().getLocation().y) % 30),30,30,screen.tools.toolNames[screen.tools.toolSelected],139,69,19,100));
                // else if(MouseInfo.getPointerInfo().getLocation().x % 30 >= 15 && MouseInfo.getPointerInfo().getLocation().y % 30 >= 15)
                    // screen.elements.add(new element((screen.currX  + MouseInfo.getPointerInfo().getLocation().x) + ((30 -(screen.currX  + MouseInfo.getPointerInfo().getLocation().x) % 30)) ,(screen.currY  + MouseInfo.getPointerInfo().getLocation().y) + ((30 - (screen.currY  + MouseInfo.getPointerInfo().getLocation().y) % 30)),30,30,screen.tools.toolNames[screen.tools.toolSelected],139,69,19,100));
                screen.elements.add(new element(screen.currX  + MouseInfo.getPointerInfo().getLocation().x - 100,screen.currY  + MouseInfo.getPointerInfo().getLocation().y - 100,200,200,screen.tools.toolNames[screen.tools.toolSelected],139,69,19,100));
                screen.currH.addItems(screen.tools.toolNames[screen.tools.toolSelected],-1,"building");
                if(screen.currH.getNumItem("thatch hut") <= 0)
                {   
                    screen.tools.toolNames[screen.tools.toolSelected] = "fist";
                }
            }
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
                            screen.currH.inven.add(new item("wooden axe tool",1,"tool"));
                            screen.currH.addItems("wood",-10,"resource");
                        }
                        else if(b.name.equals("stone axe") && screen.currH.getNumItem("wood") >= 20 && screen.currH.getNumItem("stone") >= 10)
                        {
                            screen.currH.inven.add(new item("stone axe tool",1,"tool"));
                            screen.currH.addItems("wood",-20,"resource");
                            screen.currH.addItems("stone",-10,"resource");
                        }
                        else if(b.name.equals("thatch hut") && screen.currH.getNumItem("wood") >= 10)
                        {
                            if(screen.currH.stackable("thatch hut") == false)
                                screen.currH.inven.add(new item("thatch hut",1,"building"));
                            else 
                                screen.currH.addItems("thatch hut",1,"building");
                            screen.currH.addItems("wood",-10,"resource");
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
                    Rectangle mousePointer = new Rectangle(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y,2,2);
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