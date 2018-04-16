public class timer implements Runnable
{
    int time;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    display screen;
    public timer(int t,display d)
    {
        time = t;
        screen = d;
        run();
    }

    public void run()
    {
        while(true == true)
        {
            try
            {
                Thread.sleep(time);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            if(seconds > 59)
            {
                if(minutes > 59)
                {
                    hours ++;
                    minutes = 0;
                    seconds = 0;
                }
                else 
                {
                    seconds = 0;
                    minutes ++;
                    
                }
            }
            else 
                seconds ++;
        }
    }
}