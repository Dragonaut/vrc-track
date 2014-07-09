import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class VRCTrack
{
    public static void main(String[] args)
    {
        final VRCEntryWindow ew = new VRCEntryWindow();
        final Runnable updater = new Runnable()
        {
            @Override
            public void run()
            {
                for (;;)
                {
                    ew.heartbeat();
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        break;
                    }
                }
            }
        };
        
        final Thread t_updater = new Thread(updater);
        
        ew.setVisible(true);
        t_updater.start();
    }
    
    public static final Border debugBorder = BorderFactory.createLineBorder(Color.black);

}
