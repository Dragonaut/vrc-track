import java.awt.Component;


public interface VRCFieldCalculator<T extends Component>
{
    public abstract void update(T component);
}
