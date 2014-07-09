import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

public class VRCEntryWindow extends JFrame
{
    private static final long serialVersionUID = 2665169866339080382L;

    private final BorderLayout layoutMgr = new BorderLayout(5, 5);
    private final Container rootPanel;

    private final VRCForm form = new VRCForm();

    public VRCEntryWindow()
    {
        super("Welcome to the Veterans Resource Center");
        this.setLayout(this.layoutMgr);
        this.rootPanel = this.getContentPane();

        this.rootPanel.add(this.form, BorderLayout.CENTER);
        
        this.setPreferredSize(new Dimension(480,320));
        this.setSize(480,320);
        this.setResizable(false);
        
        this.pack();
    }
    
    public void heartbeat()
    {
        this.form.heartbeat();
    }
}
