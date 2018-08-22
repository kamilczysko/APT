import java.util.Optional;

public class BasicDoubleValue extends Value {

    private Double value = null;

    public BasicDoubleValue(String value) {setValue(value);}

    public BasicDoubleValue() {}

    public BasicDoubleValue setValue(String value) {
        this.value = Double.parseDouble(value);
        return this;
    }

    public Double getValue() {
        return value;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }

    @Override
    public String toString() {
        return getValue()+"("+getWord()+")";
    }
}
