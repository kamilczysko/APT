import java.util.Optional;

public class BasicStringValue extends Value {

    private String value = null;

    public BasicStringValue(String value) {
        setStringValue(value);
    }

    public BasicStringValue() {
    }

    void setStringValue(String value) {
        this.value = value;
    }

    String getStringValue() {
        return value;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }
    @Override
    public String toString() {
        return getStringValue()+" --- ";
    }

}
