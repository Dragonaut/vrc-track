import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VRCField<V extends Component> extends JPanel implements Runnable
{
    private static final long serialVersionUID = -8436472119384361552L;

    private final JLabel label;
    private final V field;
    private final VRCFieldRetriever<V> fieldRetriever;

    private final FlowLayout flow = new FlowLayout();

    private final VRCFieldCalculator<V> calculator;

    public VRCField(String fieldName, Class<V> valueType, VRCFieldRetriever<V> ret, VRCFieldCalculator<V> calc)
    {
        super();

        //this.setBorder(VRCTrack.debugBorder);

        try
        {
            this.label = new JLabel(fieldName + ": ");
            this.label.setHorizontalTextPosition(JLabel.RIGHT);
            this.field = valueType.newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        this.setLayout(this.flow);
        this.add(this.label);
        this.add(this.field);

        this.label.setPreferredSize(new Dimension(80, 20));
        this.field.setPreferredSize(new Dimension(380, 20));
        this.fieldRetriever = ret;

        this.calculator = calc;

        this.init(this.field);
    }

    public final void heartbeat()
    {
        SwingUtilities.invokeLater(this);
    }

    @Override
    public final void run()
    {
        if (this.calculator != null) this.calculator.update(this.field);
    }

    public VRCField(String fieldName, Class<V> valueType, VRCFieldRetriever<V> ret)
    {
        this(fieldName, valueType, ret, null);
    }

    public final String toString()
    {
        return this.fieldRetriever.getValue(this.field);
    }

    public final void clearValue()
    {
        this.fieldRetriever.clear(this.field);
    }

    public void init(V field)
    {
    }
}
