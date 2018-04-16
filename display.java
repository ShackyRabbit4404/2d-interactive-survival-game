import java.awt.*;
import javax.swing.*;
import java.util.*;
public class display extends JPanel
{
    public display(hero h,ArrayList<Button> b)
    {
        super();
        setLayout(null);
        currH = h;
        Buttons = b;
        currH.setUpInventory();
        for(int i = 0; i < 10; i++)
        {
            tools.toolNames[i] =  "fist";
        }
    }
    ArrayList<Button> Buttons;
    String view;
    ArrayList<element> elements;
    hero currH;
    int currX = 0;
    int currY = 0;
    int cenX = 960;
    int cenY = 540;
    int[] xEyesPoints = new int[2];
    int[] yEyesPoints = new int[2];
    boolean W;
    boolean A;
    boolean S;
    boolean D;
    boolean space;
    boolean E = false;
    boolean Q = false;
    public void setView(String s)
    {
        view = s;
    }
    public void subtractHunger()
    {
        
    }
    public void setElements(ArrayList<element> x)
    {
        elements = x;
    }

    public void drawing()
    {
        if(isTouching(elements) == false)
        {
            if(W == true)
                currY -= 1;
            if(A == true)
                currX -= 1;
            if(S == true)
                currY += 1;
            if(D == true)
                currX += 1;
            if(W == true && isTouching(elements))
                currY += 2;
            if(A == true && isTouching(elements))
                currX += 2;
            if(S == true && isTouching(elements))
                currY -= 2;
            if(D == true && isTouching(elements))
                currX -= 2;
        }
        repaint();
    }
    int punchMoveX = 40;
    int punchMoveY = 40;
    boolean Xpunched = false;
    boolean Ypunched = false;
    Hotbar tools = new Hotbar();

    public void hit()
    {
        if(Math.random() < 0.5)
        {
            punchMoveX = 60;
            Xpunched = true;
        }
        else
        {
            punchMoveY = 60;
            Ypunched = true;
        }
        if(Xpunched == true)
        {
            for(element e: elements)
            {
                if(polyRArm.intersects(new Rectangle(e.getX() - currX,e.getY() - currY,e.getXLength(),e.getYLength())) && !e.name.equals("wall"))
                {
                    if(tools.toolNames[tools.toolSelected].equals("wooden axe tool"))
                        e.health -= 10;
                    else if(tools.toolNames[tools.toolSelected].equals("stone axe tool"))
                        e.health -= 20;
                    else if(tools.toolNames[tools.toolSelected].equals("fist"))
                        e.health -= 3;
                    space = false;
                    if(e.health <= 0)
                    {
                        if(e.name.equals("tree"))
                        {
                            if(Math.random() < 0.2)
                                currH.addItems("food",(int)((Math.random() * 5) + 1),"resource");
                            currH.addItems("wood",3,"resource");
                        }
                        if(e.name.equals("rock"))
                            currH.addItems("stone",5,"resource");
                        elements.remove(e);
                    }
                }
            }
        }
        else if(Ypunched == true)
        {
            for(element e: elements)
            {
                if(polyLArm.intersects(new Rectangle(e.getX() - currX,e.getY() - currY,e.getXLength(),e.getYLength())) && !e.name.equals("wall"))
                {
                    if(tools.toolNames[tools.toolSelected].equals("wooden axe tool"))
                        e.health -= 6;
                    else if(tools.toolNames[tools.toolSelected].equals("stone axe tool"))
                        e.health -= 12;
                    else if(tools.toolNames[tools.toolSelected].equals("fist"))
                        e.health -= 3;
                    space = false;
                    if(e.health <= 0)
                    {
                        if(e.name.equals("tree"))
                            currH.addItems("wood",3,"resource");
                        if(e.name.equals("rock"))
                            currH.addItems("stone",5,"resource");
                        elements.remove(e);
                    }
                }
            }
        }
        space = false;
        Xpunched = false;
        Ypunched = false;
    }

    public void setW(boolean w)
    {
        W = w;
    }

    public void setSpace(boolean s)
    {
        space = s;
    }

    public void setA(boolean w)
    {
        A = w;
    }

    public void setS(boolean w)
    {
        S = w;
    }

    public void setD(boolean w)
    {
        D = w;
    }

    public boolean isTouching(ArrayList<element> e)
    {
        for(element a: e)
        {
            Rectangle other = new Rectangle(a.getX() - currX,a.getY() - currY,a.getXLength(),a.getYLength());
            if(polyH.intersects(other))
                return true;
        }
        return false;
    }
    int[] xPoints = new int[4];
    int[] yPoints = new int[4];
    int[] xRightArmPoints = new int[4];
    int[] yRightArmPoints = new int[4];
    int[] xLeftArmPoints = new int[4];
    int[] yLeftArmPoints = new int[4];
    Polygon polyH = new Polygon(xPoints,yPoints,4);
    Polygon polyRArm = new Polygon(xRightArmPoints,yRightArmPoints,4);
    Polygon polyLArm = new Polygon(xLeftArmPoints,yLeftArmPoints,4);
    int[][] points;
    ArrayList<Button> inventoryItems;
    int maxPop = 0;
    int currPop = 0;
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(view.equals("world"))
        {
            Buttons.get(1).visible = false;
            Buttons.get(2).visible = false;
            Buttons.get(3).visible = false;
            E = false;
            Q = false;
            Buttons.get(0).visible = true;
            g.setColor(new Color(74,184,71));
            g.fillRect(0,0,1920,1080);
            g.setColor(Color.RED);
            tri t1 = new tri(Math.sqrt((MouseInfo.getPointerInfo().getLocation().x-960)*(MouseInfo.getPointerInfo().getLocation().x-960)+(MouseInfo.getPointerInfo().getLocation().y-540)*(MouseInfo.getPointerInfo().getLocation().y-540)),MouseInfo.getPointerInfo().getLocation().x-960);
            t1.calcAngle();
            tri t2 = new tri(t1.angle);
            if(MouseInfo.getPointerInfo().getLocation().y == 565)
            {
                xPoints[0] = 985;
                xPoints[1] = 985;
                xPoints[2] = 935;
                xPoints[3] = 935;

                yPoints[0] = 515;
                yPoints[1] = 565;
                yPoints[2] = 565;
                yPoints[3] = 515;

                xEyesPoints[0] = 950;
                xEyesPoints[1] = 970;

                yEyesPoints[0] = 530;
                yEyesPoints[1] = 550;
            }
            else if(MouseInfo.getPointerInfo().getLocation().y < 550)
            {
                xPoints[0] = (int)((25 * Math.cos(t1.angle)) - (-25 * Math.sin(t1.angle))) + 960;
                xPoints[1] = (int)((25 * Math.cos(t1.angle)) - (25 * Math.sin(t1.angle))) + 960;
                xPoints[2] = (int)((-25 * Math.cos(t1.angle)) - (25 * Math.sin(t1.angle))) + 960;
                xPoints[3] = (int)((-25 * Math.cos(t1.angle)) - (-25 * Math.sin(t1.angle))) + 960;

                yPoints[0] = (int)((25 * Math.sin(t1.angle)) + (-25 * Math.cos(t1.angle))) + 540;
                yPoints[1] = (int)((25 * Math.sin(t1.angle)) + (25 * Math.cos(t1.angle))) + 540;
                yPoints[2] = (int)((-25 * Math.sin(t1.angle)) + (25 * Math.cos(t1.angle))) + 540;
                yPoints[3] = (int)((-25 * Math.sin(t1.angle)) + (-25 * Math.cos(t1.angle))) + 540;

                xEyesPoints[0] = (int)((10 * Math.cos(t1.angle)) - (-12 * Math.sin(t1.angle))) + 955;
                xEyesPoints[1] = (int)((-10 * Math.cos(t1.angle)) - (-12 * Math.sin(t1.angle))) + 955;

                yEyesPoints[0] = (int)((10 * Math.sin(t1.angle)) + (-12 * Math.cos(t1.angle))) + 540;
                yEyesPoints[1] = (int)((-10 * Math.sin(t1.angle)) + (-12 * Math.cos(t1.angle))) + 540;

                xRightArmPoints[0] = (int)((22 * Math.cos(t1.angle)) - (-1 * (punchMoveX - 38) * Math.sin(t1.angle))) + 960;
                xRightArmPoints[1] = (int)((32 * Math.cos(t1.angle)) - (-1 * (punchMoveX - 38) * Math.sin(t1.angle))) + 960;
                xRightArmPoints[2] = (int)((32 * Math.cos(t1.angle)) - (-1 * punchMoveX * Math.sin(t1.angle))) + 960;
                xRightArmPoints[3] = (int)((22 * Math.cos(t1.angle)) - (-1 * punchMoveX * Math.sin(t1.angle))) + 960;

                yRightArmPoints[0] = (int)((22 * Math.sin(t1.angle)) + (-1 * (punchMoveX - 38) * Math.cos(t1.angle))) + 540;
                yRightArmPoints[1] = (int)((32 * Math.sin(t1.angle)) + (-1 * (punchMoveX - 38) * Math.cos(t1.angle))) + 540;
                yRightArmPoints[2] = (int)((32 * Math.sin(t1.angle)) + (-1 * punchMoveX * Math.cos(t1.angle))) + 540;
                yRightArmPoints[3] = (int)((22 * Math.sin(t1.angle)) + (-1 * punchMoveX * Math.cos(t1.angle))) + 540;

                xLeftArmPoints[0] = (int)((-22 * Math.cos(t1.angle)) - (-1 * (punchMoveY - 38) * Math.sin(t1.angle))) + 960;
                xLeftArmPoints[1] = (int)((-32 * Math.cos(t1.angle)) - (-1 * (punchMoveY - 38) * Math.sin(t1.angle))) + 960;
                xLeftArmPoints[2] = (int)((-32 * Math.cos(t1.angle)) - (-1 * punchMoveY * Math.sin(t1.angle))) + 960;
                xLeftArmPoints[3] = (int)((-22 * Math.cos(t1.angle)) - (-1 * punchMoveY * Math.sin(t1.angle))) + 960;

                yLeftArmPoints[0] = (int)((-22 * Math.sin(t1.angle)) + (-1 * (punchMoveY - 38) * Math.cos(t1.angle))) + 540;
                yLeftArmPoints[1] = (int)((-32 * Math.sin(t1.angle)) + (-1 * (punchMoveY - 38) * Math.cos(t1.angle))) + 540;
                yLeftArmPoints[2] = (int)((-32 * Math.sin(t1.angle)) + (-1 * punchMoveY * Math.cos(t1.angle))) + 540;
                yLeftArmPoints[3] = (int)((-22 * Math.sin(t1.angle)) + (-1 * punchMoveY * Math.cos(t1.angle))) + 540;
            }
            else
            {
                t1.angle = (t1.angle * -1);
                xPoints[0] = (int)((25 * Math.cos(t1.angle)) - (-25 * Math.sin(t1.angle))) + 960;
                xPoints[1] = (int)((25 * Math.cos(t1.angle)) - (25 * Math.sin(t1.angle))) + 960;
                xPoints[2] = (int)((-25 * Math.cos(t1.angle)) - (25 * Math.sin(t1.angle))) + 960;
                xPoints[3] = (int)((-25 * Math.cos(t1.angle)) - (-25 * Math.sin(t1.angle))) + 960;

                yPoints[0] = (int)((25 * Math.sin(t1.angle)) + (-25 * Math.cos(t1.angle))) + 540;
                yPoints[1] = (int)((25 * Math.sin(t1.angle)) + (25 * Math.cos(t1.angle))) + 540;
                yPoints[2] = (int)((-25 * Math.sin(t1.angle)) + (25 * Math.cos(t1.angle))) + 540;
                yPoints[3] = (int)((-25 * Math.sin(t1.angle)) + (-25 * Math.cos(t1.angle))) + 540;

                xEyesPoints[0] = (int)((10 * Math.cos(t1.angle)) - (12 * Math.sin(t1.angle))) + 955;
                xEyesPoints[1] = (int)((-10 * Math.cos(t1.angle)) - (12 * Math.sin(t1.angle))) + 955;

                yEyesPoints[0] = (int)((10 * Math.sin(t1.angle)) + (12 * Math.cos(t1.angle))) + 540;
                yEyesPoints[1] = (int)((-10 * Math.sin(t1.angle)) + (12 * Math.cos(t1.angle))) + 540;

                xRightArmPoints[0] = (int)((22 * Math.cos(t1.angle)) - ((punchMoveX - 38) * Math.sin(t1.angle))) + 960;
                xRightArmPoints[1] = (int)((32 * Math.cos(t1.angle)) - (punchMoveX - 38) * Math.sin(t1.angle)) + 960;
                xRightArmPoints[2] = (int)((32 * Math.cos(t1.angle)) - (punchMoveX * Math.sin(t1.angle))) + 960;
                xRightArmPoints[3] = (int)((22 * Math.cos(t1.angle)) - (punchMoveX * Math.sin(t1.angle))) + 960;

                yRightArmPoints[0] = (int)((22 * Math.sin(t1.angle)) + ((punchMoveX - 38) * Math.cos(t1.angle))) + 540;
                yRightArmPoints[1] = (int)((32 * Math.sin(t1.angle)) + ((punchMoveX - 38) * Math.cos(t1.angle))) + 540;
                yRightArmPoints[2] = (int)((32 * Math.sin(t1.angle)) + (punchMoveX * Math.cos(t1.angle))) + 540;
                yRightArmPoints[3] = (int)((22 * Math.sin(t1.angle)) + (punchMoveX * Math.cos(t1.angle))) + 540;

                xLeftArmPoints[0] = (int)((-22 * Math.cos(t1.angle)) - ((punchMoveY - 38) * Math.sin(t1.angle))) + 960;
                xLeftArmPoints[1] = (int)((-32 * Math.cos(t1.angle)) - ((punchMoveY - 38) * Math.sin(t1.angle))) + 960;
                xLeftArmPoints[2] = (int)((-32 * Math.cos(t1.angle)) - (punchMoveY * Math.sin(t1.angle))) + 960;
                xLeftArmPoints[3] = (int)((-22 * Math.cos(t1.angle)) - (punchMoveY * Math.sin(t1.angle))) + 960;

                yLeftArmPoints[0] = (int)((-22 * Math.sin(t1.angle)) + ((punchMoveY - 38) * Math.cos(t1.angle))) + 540;
                yLeftArmPoints[1] = (int)((-32 * Math.sin(t1.angle)) + ((punchMoveY - 38) * Math.cos(t1.angle))) + 540;
                yLeftArmPoints[2] = (int)((-32 * Math.sin(t1.angle)) + (punchMoveY * Math.cos(t1.angle))) + 540;
                yLeftArmPoints[3] = (int)((-22 * Math.sin(t1.angle)) + (punchMoveY * Math.cos(t1.angle))) + 540;
            }
            //g.fillRect(935,515,50,50);
            if(punchMoveX > 40)
                punchMoveX --;
            if(punchMoveY > 40)
                punchMoveY --;
            g.setColor(Color.RED);
            polyH = new Polygon(xPoints,yPoints,4);
            polyRArm = new Polygon(xRightArmPoints,yRightArmPoints,4);
            polyLArm = new Polygon(xLeftArmPoints,yLeftArmPoints,4);
            g.fillPolygon(xPoints,yPoints,4);
            g.setColor(Color.BLACK);
            g.fillOval(xEyesPoints[0],yEyesPoints[0],8,8);
            g.fillOval(xEyesPoints[1],yEyesPoints[1],8,8);
            g.setColor(new Color(239,223,173));
            g.fillPolygon(polyRArm);
            g.fillPolygon(polyLArm);

            for(element q: elements)
            {
                int[] rgb = q.getColor();
                g.setColor(new Color(rgb[0],rgb[1],rgb[2]));
                g.fillRect(q.getX()-currX,q.getY()-currY,q.getXLength(),q.getYLength());
                if(q.health < q.maxHealth)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect((q.getX() - currX),(q.getY() - currY) + q.getYLength() + 9, q.maxHealth + 2, 22);
                    g.setColor(Color.BLACK);
                    g.drawRect((q.getX() - currX),(q.getY() - currY) + q.getYLength() + 9, q.maxHealth + 2, 22);
                    g.setColor(Color.GREEN);
                    g.fillRect((q.getX() - currX) + 1,(q.getY() - currY) + q.getYLength() + 10,q.health,21);
                }

            }
            for(Button b: Buttons)
            {
                if(b.visible == true)
                {
                    Rectangle mousePointer = new Rectangle(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y,1,1);
                    if(mousePointer.intersects(new Rectangle(b.X,b.Y + 40,b.XLength,b.YLength)))
                    {
                        g.setColor(Color.BLACK);
                        g.fillRect(b.X,b.Y,b.XLength,b.YLength);
                        g.setColor(Color.WHITE);
                        g.drawRect(b.X,b.Y,b.XLength,b.YLength);
                        g.drawString(b.name,b.X + 5, b.Y + 15);
                    }
                    else 
                    {
                        g.setColor(Color.WHITE);
                        g.fillRect(b.X,b.Y,b.XLength,b.YLength);
                        g.setColor(Color.BLACK);
                        g.drawRect(b.X,b.Y,b.XLength,b.YLength);
                        g.drawString( b.name,b.X + 5, b.Y + 15);
                    }
                }
            }

            for(int i = 0; i < 10; i++)
            {
                if(i == tools.toolSelected)
                {
                    if(tools.toolNames[i].equals("wooden axe tool"))
                    {
                        g.setColor(new Color(205,81,39));
                        g.drawLine(xRightArmPoints[3],yRightArmPoints[3],xLeftArmPoints[3],yLeftArmPoints[3]);
                    }
                    else if(tools.toolNames[i].equals("stone axe tool"))
                    {
                        g.setColor(Color.BLACK);
                        g.drawLine(xRightArmPoints[3],yRightArmPoints[3],xLeftArmPoints[3],yLeftArmPoints[3]);
                    }
                    else if(tools.toolNames[i].equals("thatch hut"))
                    {
                        g.setColor(Color.RED);
                        g.fillRect(MouseInfo.getPointerInfo().getLocation().x - 100,MouseInfo.getPointerInfo().getLocation().y - 100,200,200);
                    }
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(750,900,422,44);
            g.setColor(Color.BLACK);
            g.drawRect(750,900,422,44);
            int count = 0;
            for(int i = 752; i < 750 + 416; i += 42)
            {
                g.setColor(Color.BLACK);
                g.drawRect(i,902,40,40);
                if(tools.toolNames[count].equals("wooden axe tool"))
                {
                    g.setColor(new Color(205,81,39));
                    g.fillRect(i + 2,904,38,38);
                }
                else if(tools.toolNames[count].equals("stone axe tool"))
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(i + 2,904,38,38);
                }
                else if(tools.toolNames[count].equals("thatch hut"))
                {
                    g.setColor(new Color(139,69,19));
                    g.fillRect(i + 2,904,38,38);
                }
                count ++;
            }
            g.setColor(Color.WHITE);
            g.drawRect((tools.toolSelected * 42)+752,902,40,40);
            g.setFont(new Font("1", Font.PLAIN,50)); 
            g.drawString("Popluation: " + currPop + " / " + maxPop,10,50);
        }
        else if(view.equals("inventory"))
        {
            E = true;
            Q = false;
            Buttons.get(0).visible = false;
            Buttons.get(1).visible = false;
            Buttons.get(2).visible = false;
            g.setColor(new Color(74,184,71));
            g.fillRect(0,0,1920,1080);
            g.setColor(Color.GRAY);
            g.fillRect(50,50,1800,900);
            g.setColor(Color.BLACK);
            g.drawRect(50,50,1800,900);
            g.drawLine(490,75,490,925);
            g.setFont(new Font("1", Font.PLAIN,50)); 
            g.setColor(Color.WHITE);
            g.drawString("INVENTORY",100,150);
            g.setFont(new Font("2",Font.PLAIN,40));
            g.drawString("Wood   " + currH.getNumItem("wood"),150,250);
            g.drawString("Stone   " + currH.getNumItem("stone"),150,300);
            g.drawString("Food " + currH.getNumItem("food"),150,350);
            int x = 515;
            int y = 75;
            inventoryItems = new ArrayList<Button>();
            for(item i: currH.inven)
            {
                if(!i.name.equals("wood") && !i.name.equals("stone") && y < 900 && !i.name.equals("food"))
                {
                    inventoryItems.add(new Button(x,y,50,50,i.name,true,true));
                    if(x < 1775)
                        x+= 75;
                    else
                    {
                        x = 515;
                        y += 75;
                    }
                }

            }
            x = 515;
            y = 75;
            for(Button i: inventoryItems)
            {
                if(!i.name.equals("wood") && !i.name.equals("stone") && y < 900)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(x,y,50,50);
                    if(x < 1775)
                        x += 75;
                    else
                    {
                        x = 515;
                        y += 75;
                    }
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(750,900,422,44);
            g.setColor(Color.BLACK);
            g.drawRect(750,900,422,44);
            int count = 0;
            for(int i = 752; i < 750 + 416; i += 42)
            {
                g.setColor(Color.BLACK);
                g.drawRect(i,902,40,40);
                if(tools.toolNames[count].equals("wooden axe tool"))
                {
                    g.setColor(new Color(205,81,39));
                    g.fillRect(i + 2,904,38,38);
                }
                else if(tools.toolNames[count].equals("stone axe tool"))
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(i + 2,904,38,38);
                }
                else if(tools.toolNames[count].equals("thatch hut"))
                {
                    g.setColor(new Color(139,69,19));
                    g.fillRect(i + 2,904,38,38);
                }
                count ++;
            }

            g.setColor(Color.WHITE);
            g.drawRect((tools.toolSelected * 42)+752,902,40,40);
        }
        else if(view.equals("crafting"))
        {
            Q = true;
            E = false;
            Buttons.get(0).visible = false;
            g.setColor(new Color(74,184,71));
            g.fillRect(0,0,1920,1080);
            g.setColor(Color.GRAY);
            g.fillRect(50,50,1800,900);
            g.setColor(Color.BLACK);
            g.drawRect(50,50,1800,900);
            g.setFont(new Font("1", Font.PLAIN,50)); 
            g.setColor(Color.WHITE);
            g.drawString("CRAFTING",100,150);
            g.setFont(new Font("2",Font.PLAIN,40));
            int x = 150;
            int y = 250;
            Buttons.get(1).visible = true;
            Buttons.get(2).visible = true;
            Buttons.get(3).visible = true;
            for(Button b: Buttons)
            {
                if(b.visible == true)
                {
                    if(b.name.equals("wooden axe") && currH.inven.get(currH.getItemNum("wood")).quantity >= 10)
                        b.clickable = true;
                    else if(b.name.equals("wooden axe"))
                        b.clickable = false;
                    else if(b.name.equals("stone axe") && currH.inven.get(currH.getItemNum("wood")).quantity >= 20 && currH.inven.get(currH.getItemNum("stone")).quantity >= 10)
                        b.clickable = true;
                    else if(b.name.equals("stone axe"))
                        b.clickable = false;
                    else if(b.name.equals("thatch hut") && currH.inven.get(currH.getItemNum("wood")).quantity >= 10)
                        b.clickable = true;
                    else if(b.name.equals("thatch hut"))
                        b.clickable = false;
                }
            }
            for(Button b: Buttons)
            {
                if(b.visible == true)
                {
                    g.setFont(new Font("2",Font.PLAIN,40));
                    if(b.clickable == true)
                    {
                        Rectangle mousePointer = new Rectangle(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y,1,1);
                        if(mousePointer.intersects(new Rectangle(b.X,b.Y + 40,b.XLength,b.YLength)))
                        {
                            g.setColor(Color.BLACK);
                            g.fillRect(b.X,b.Y,b.XLength,b.YLength);
                            g.setColor(Color.WHITE);
                            g.drawRect(b.X,b.Y,b.XLength,b.YLength);
                            g.drawString(b.name,b.X + 15,b.Y + 35);
                            g.setFont(new Font("3",Font.PLAIN,20));
                            if(b.name.equals("wooden axe"))
                                g.drawString("x10 wood", b.X + 15, b.Y + 60);
                            else if(b.name.equals("stone axe"))
                                g.drawString("x20 wood + x10 stone",b.X + 15,b.Y + 60);
                            else if(b.name.equals("thatch hut"))
                                g.drawString("x10 wood",b.X + 15,b.Y + 60);
                        }
                        else
                        {
                            g.setColor(Color.WHITE);
                            g.fillRect(b.X,b.Y,b.XLength,b.YLength);
                            g.setColor(Color.BLACK);
                            g.drawRect(b.X,b.Y,b.XLength,b.YLength);
                            g.drawString(b.name,b.X + 15,b.Y + 35);
                        }
                    }
                    else
                    {
                        g.setColor(Color.BLACK);
                        g.fillRect(b.X,b.Y,b.XLength,b.YLength);
                        g.setColor(Color.RED);
                        g.drawRect(b.X,b.Y,b.XLength,b.YLength);
                        g.drawString(b.name,b.X + 15, b.Y + 35);
                        g.setFont(new Font("3",Font.PLAIN,20));
                        if(b.name.equals("wooden axe"))
                            g.drawString("x10 wood",b.X + 15,b.Y + 60);
                        else if(b.name.equals("stone axe"))
                            g.drawString("x20 wood + x10 stone",b.X + 15,b.Y + 60);
                        else if(b.name.equals("thatch hut"))
                            g.drawString("x10 wood",b.X + 15,b.Y + 60);
                    }
                }
            }
        }
        try{
            // Thread.sleep(16);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        drawing();
    }
    public void maxPopChange(int c)
    {
        maxPop += c;
    }
    public void popChange(int c)
    {
        currPop += c;
    }
    public int[][] findPoints(int x, int y,int xx, int yy)
    {
        int[][] ret = new int[4][2];
        double[] mid = new double[4];
        double m;
        if(xx == x || yy == y)
        {
            ret[0][0] = 935;
            ret[0][1] = 515;
            ret[1][0] = 985;
            ret[1][1] = 515;
            ret[2][0] = 935;
            ret[2][1] = 565;
            ret[3][0] = 985;
            ret[3][1] = 565;
            return ret;
        }
        else
        {
            m = ((xx - x)/(yy - y));
            mid[0] = x-25/Math.sqrt(1+(m*m));
            mid[1] = y-(m*25)/Math.sqrt(1+(m*m));
            mid[2] = x+25/Math.sqrt(1+(m*m));
            mid[3] = y+25*m/Math.sqrt(1+(m*m));
        }
        m = ((xx-x)/(yy-y))*-1;
        ret[0][0] = (int)(0.5+(mid[0]+25/Math.sqrt(1+(m*m))));
        ret[0][1] = (int)(0.5+(mid[1]+25/Math.sqrt(1+(m*m))));

        ret[1][0] = (int)(0.5+(mid[0]-25/Math.sqrt(1+(m*m))));
        ret[1][1] = (int)(0.5+(mid[1]-25/Math.sqrt(1+(m*m))));

        ret[2][0] = (int)(0.5+(mid[2]+25/(Math.sqrt(1+(m*m)))));
        ret[2][1] = (int)(0.5+(mid[3]+25/(Math.sqrt(1+(m*m)))));

        ret[3][0] = (int)(0.5+(mid[2]-25/(Math.sqrt(1+(m*m)))));
        ret[3][1] = (int)(0.5+(mid[3]-25/(Math.sqrt(1+(m*m)))));
        return ret;
    }
}