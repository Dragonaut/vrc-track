import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VRCForm extends JPanel
{
    private static final long serialVersionUID = -3343567710361293451L;

    private final BoxLayout layoutMgr;
    
    private final JButton submitButton;
    private final JButton clearButton;
    private final JPanel buttonPanel;

    /* @formatter:off */
    private List<VRCField<? extends Component>> fields = new ArrayList<VRCField<? extends Component>>(); 
    
    /* @formatter:on */

    public VRCForm()
    {
        this.fields.add(VRCCommonFields.firstname);
        this.fields.add(VRCCommonFields.lastname);
        this.fields.add(VRCCommonFields.timestamp);
        this.fields.add(VRCCommonFields.reason);
        
       this.layoutMgr = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(this.layoutMgr);
        
        
        for (final VRCField<?> f : fields)
        {
            this.add(f);
        }
        
        this.submitButton = new JButton("Submit");
        this.clearButton = new JButton("Clear Form");
        this.buttonPanel = new JPanel();
        this.buttonPanel.add(this.submitButton);
        this.buttonPanel.add(this.clearButton);

        this.add(this.buttonPanel);

        this.clearButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for (final VRCField<? extends Component> field : fields)
                {
                    field.clearValue();
                }
            }
        });

        this.submitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                final File f = new File("log.csv");
                try
                {
                    System.out.println(f.createNewFile());

                    final FileOutputStream fos = new FileOutputStream(f, true);
                    final OutputStreamWriter out = new OutputStreamWriter(fos);
                    out.write(VRCForm.this.getContents());
                    out.flush();
                    out.close();
                    fos.close();
                    clearButton.doClick();
                    
               	 JLabel response = new JLabel("Thanks for coming to the VRC!");
                    JOptionPane.showMessageDialog(null, response, "Confirmation",
                    		JOptionPane.PLAIN_MESSAGE); setVisible(true); //display confirmation message
                }
                catch (final IOException ex)
                {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
            }
        });
    }

    public void heartbeat()
    {
        for (final VRCField<?> f : fields)
            f.heartbeat();
    }

    private final String getContents()
    {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.fields.size(); i++)
        {
            sb.append(this.fields.get(i).toString());
            if (i != this.fields.size() - 1)
            {
                sb.append(",");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

}
