public class tri
{
    double hyp;
    double angle;
    double opp;
    double adj;
    public tri(double h,double a)
    {
        hyp = h;
        adj = a;
        opp = 0;
        angle = 0;
    }
    public tri(double a)
    {
        hyp = 25;
        adj = 0;
        opp = 0;
        angle = a;
    }
    public double calcAngle()
    {
        angle = Math.asin(Math.sin(adj/hyp));
        return angle;
    }
    public double calcOpp()
    {
        opp = Math.tan(angle)*adj;
        return opp;
    }
    public double calcAdj()
    {
        adj = Math.cos(angle)*hyp;
        return adj;
    }
}