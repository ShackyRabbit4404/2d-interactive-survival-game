import java.util.*;
public class hero
{
    int health = 100;
    int maxHealth = 100;
    ArrayList<item> inven = new ArrayList<item>();
    public void setUpInventory()
    {
        inven.add(new item("wood",0));
        inven.add(new item("stone",0));
    }
    public void addItems(String s,int n)
    {
        for(item i: inven)
        {
            if(i.name.equals(s))
                i.quantity += n;    
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
}