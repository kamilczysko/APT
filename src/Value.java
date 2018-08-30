import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class Value {
    private List<Value> complexParameters = new LinkedList<>();
    private String mainWord = null;

    public Value setMainWord(String word) {
        mainWord = word;

        return this;
    }

    public void addParameter(Value parameter) {
        complexParameters.add(parameter);
    }

    public List<Value> getParameters() {
        return complexParameters;
    }

    public int sizeOfArrayOfParametersList() {
        return complexParameters.size();
    }

    public String getMainWord() {
        return mainWord;
    }

    public boolean hasWord() {
        return Optional.ofNullable(mainWord).isPresent();
    }

    public boolean isComplexValue() {
        return (this instanceof ComplexValue);
    }

    ;

    abstract boolean hasBasicValue();
}