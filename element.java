public class element
{
    private int X;
    private int Y;
    private int xLength;
    private int yLength;
    String name;
    private int red;
    private int green;
    private int blue;
    int health;
    int maxHealth;
    public element(int x,int y,int xL,int yL,String n,int r,int g,int b,int h)
    {
        X = x;
        Y = y;
        xLength = xL;
        yLength = yL;
        name = n;
        red = r;
        green = g;
        blue = b;
        health = h;
        maxHealth = h;
    }
    public int getX()
    {
        return X;
    }
    public int getY()
    {
        return Y;
    }
    public int getXLength()
    {
        return xLength;
    }
    public int getYLength()
    {
        return yLength;
    }
    public String getName()
    {
        return name;
    }
    public int[] getColor()
    {
        int[] ret = new int[3];
        ret[0] = red;
        ret[1] = blue;
        ret[2] = green;
        return ret;
    }
}