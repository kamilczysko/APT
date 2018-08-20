import java.util.Optional;

public class BasicStringValue extends Value {

    private String value = null;

    void setDoubleValue(String value) {
        this.value = value;
    }

    String getStringValue() {
        return value;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }
}
