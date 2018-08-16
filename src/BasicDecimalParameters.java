import java.util.LinkedList;
import java.util.List;

public class BasicDecimalParameters implements BasicParameters {

    private List<Double> parameters = new LinkedList<>();

    @Override
    public BasicParameters addParameter(String param) {
        parameters.add(Double.parseDouble(param));
        return this;
    }

    public List<Double> getParameters(){
        return parameters;
    }

    @Override
    public String toString() {
        return parameters.toString();
    }
}
