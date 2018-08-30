import java.util.Optional;

public class BasicIntValue extends Value {
    private Integer actualValue = null;

    public BasicIntValue(String value) {
        setValue(value);
    }

    public BasicIntValue() {}

    public BasicIntValue setValue(String value) {
        actualValue = Integer.parseInt(value);
        return this;
    }

    public Integer getValue() {
        return actualValue;
    }

    @Override
    public boolean hasBasicValue() {
        return Optional.ofNullable(actualValue).isPresent();
    }

    @Override
    public String toString() {
        return getValue() + "(" + getMainWord() + ")";
    }
}
