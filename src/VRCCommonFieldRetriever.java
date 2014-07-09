import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VRCCommonFieldRetriever
{
    private VRCCommonFieldRetriever()
    {
    }

    public static final VRCFieldRetriever<JTextField> JTextField = new VRCFieldRetriever<JTextField>()
    {
        @Override
        public String getValue(JTextField field)
        {
            return field.getText();
        }

        public void clear(JTextField field)
        {
            field.setText("");
        }
    };
    public static final VRCFieldRetriever<JComboBox> JComboBox = new VRCFieldRetriever<JComboBox>()
    {
        @Override
        public String getValue(JComboBox field)
        {
            if (field.getSelectedItem() != null)
            {
                return field.getSelectedItem().toString();
            }
            else
            {
                return "(none)";
            }
        }

        public void clear(JComboBox field)
        {
            field.setSelectedIndex(0);
        }
    };
}
