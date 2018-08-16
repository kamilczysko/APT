import java.util.LinkedList;
import java.util.List;

public class BasicStringParameter implements BasicParameters {


    private List<String> stringParam = new LinkedList<>();

    @Override
    public BasicParameters addParameter(String param) {
        stringParam.add(param);
        return this;
    }

    @Override
    public List getParameters() {
        return stringParam;
    }


    @Override
    public String toString() {
        return getParameters().toString();
    }
}
