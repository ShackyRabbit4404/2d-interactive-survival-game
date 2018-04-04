public class Button 
{
    int X;
    int Y;
    int XLength;
    int YLength;
    String name;
    boolean visible;
    boolean clickable;
    
    public Button(int x,int y,int xl,int yl,String n,boolean v,boolean c)
    {
        X = x;
        Y = y;
        XLength = xl;
        YLength = yl;
        name = n;
        visible = v;
        clickable = c;
    }
    
    
}