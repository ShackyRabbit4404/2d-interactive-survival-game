import java.util.*;
public class hero
{
    int health = 100;
    int maxHealth = 100;
    ArrayList<item> inven = new ArrayList<item>();
    public void setUpInventory()
    {
        inven.add(new item("wood",0,"resource"));
        inven.add(new item("stone",0,"resource"));
    }

    public void addItems(String s,int n,String t)
    {
        for(item i: inven)
        {
            if(i.name.equals(s) && (n * (-1)) < 0)
            {   
                for(int a = 0; a < n; a++)
                {
                    if(stackable(s))
                        i.quantity ++;
                    else 
                        inven.add(new item(s,1,t));
                }
            }
            else if(i.name.equals(s) && (n * (-1)) > 0)
            {
                for(int a = n * -1; a > 0; a--)
                {
                    if(i.quantity == 1 && !i.name.equals("wood") && !i.name.equals("stone"))
                        inven.remove(i);
                    else 
                        i.quantity --;
                }
            }
        }
    }

    public int getItemNum(String n)
    {
        for(int i = 0; i < inven.size(); i++)
        {
            if(inven.get(i).name.equals(n))
                return i;
        }
        return 0;
    }

    public int getNumItem(String n)
    {
        for(item i: inven)
        {
            if(i.name.equals(n))
                return i.quantity;
        }
        return 0;
    }

    public boolean stackable(String s)
    {
        for(item i: inven)
        {
            if(i.name.equals(s) && i.quantity < 32)
                return true;
        }   
        return false;
    }

    public boolean contains(String s)
    {
        for(item i: inven)
        {
            if(s.equals(i.name))
                return true;
        }
        return false;
    }   
}