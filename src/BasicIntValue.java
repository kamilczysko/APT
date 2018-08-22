import java.util.Optional;

public class BasicIntValue extends Value {

    private Integer value = null;

    public BasicIntValue(String value) {
        setValue(value);
    }

    public BasicIntValue() {}

    public BasicIntValue setValue(String value) {
        this.value = Integer.parseInt(value);
        return this;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }

    @Override
    public String toString() {
        return getValue()+"("+getWord()+")";
    }
}
