public class timer implements Runnable
{
    int timeDelay;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    display screen;
    public timer(int t,display d)
    {
        timeDelay = t;
        screen = d;
        run();
    }

    public void run()
    {

        while(true == true)
        {
            try
            {
                Thread.sleep(timeDelay);
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
                if(screen.currH.hunger > 0)
                    screen.changeHunger(-1);
            }
            else 
                seconds ++;
            if(screen.currH.hunger <= 0) 
                screen.currH.health -= 2;
            screen.setTime(seconds,minutes,hours);
        }
    }
}