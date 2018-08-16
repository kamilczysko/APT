import java.util.LinkedList;
import java.util.List;

public class BasicIntParameters implements BasicParameters {

    private List<Integer> parameters = new LinkedList<>();
    @Override
    public BasicParameters addParameter(String param) {
        parameters.add(Integer.parseInt(param));
        return this;
    }

    @Override
    public List getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return getParameters().toString();
    }
}
