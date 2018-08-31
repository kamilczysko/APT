import java.util.Optional;

public class BasicStringValue extends Value {
    private String actualValue = null;

    public BasicStringValue(String mValue) { setActualValue(mValue); }

    public BasicStringValue() {}

    public BasicStringValue setActualValue(String value) {
        actualValue = value;
        return this;
    }

    public String getActualValue() { return actualValue; }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(actualValue).isPresent();
    }

    @Override
    public String toString() {
        return getActualValue() + "(" + getMainWord() + ")";
    }

}
