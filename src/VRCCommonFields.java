import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VRCCommonFields
{
    public static VRCField<?> timestamp = new VRCField<JTextField>("Time", JTextField.class,
            VRCCommonFieldRetriever.JTextField, new VRCFieldCalculator<JTextField>()
            {
                @Override
                public void update(JTextField component)
                {
                    component.setText(new Date().toString());
                }
            })
    {
        private static final long serialVersionUID = -402572802160834640L;

        @Override
        public void init(JTextField f)
        {
            f.setEditable(false);
        }
    };

    public static VRCField<? extends Component> firstname = new VRCField<JTextField>("First Name", JTextField.class,
            VRCCommonFieldRetriever.JTextField);
    public static VRCField<? extends Component> lastname = new VRCField<JTextField>("Last Name", JTextField.class,
            VRCCommonFieldRetriever.JTextField);
    public static VRCField<? extends Component> reason = new VRCField<JComboBox>("Reason", JComboBox.class,
            VRCCommonFieldRetriever.JComboBox)
    {
        private static final long serialVersionUID = 6999542942631253122L;

        @Override
        public void init(JComboBox field)
        {
            final File f = new File("reasons.txt");
            if (f.exists())
            {
                try
                {
                    final Scanner scan = new Scanner(f);
                    while (scan.hasNextLine())
                    {
                        final String text = scan.nextLine();
                        field.addItem(text);
                    }
                }
                catch (final FileNotFoundException e)
                {
                    field.addItem("reaons.txt cannot be opened");
                    e.printStackTrace();
                }
            }
            else
            {
                field.addItem("reasons.txt does not exist");
            }
        }
    };

}
