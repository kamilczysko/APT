import java.util.Optional;

public class BasicStringValue extends Value {

    private String value = null;

    public BasicStringValue(String value) {setValue(value);}

    public BasicStringValue() {}

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }

    @Override
    public String toString() {
        return getValue()+"";
    }

}
