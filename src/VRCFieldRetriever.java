import java.awt.Component;

public interface VRCFieldRetriever<T extends Component>
{
    public abstract String getValue(T field);

    public abstract void clear(T field);

}
