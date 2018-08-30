import java.util.Optional;

public class BasicDoubleValue extends Value {
    private Double actualValue = null;

    public BasicDoubleValue(String value) {
        setValue(value);
    }

    public BasicDoubleValue() {
    }

    public BasicDoubleValue setValue(String value) {
        actualValue = Double.parseDouble(value);
        return this;
    }

    public Double getValue() {
        return actualValue;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(actualValue).isPresent();
    }

    @Override
    public String toString() {
        return getValue() + "(" + getMainWord() + ")";
    }

}
