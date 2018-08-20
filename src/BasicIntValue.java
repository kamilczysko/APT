import java.util.Optional;

public class BasicIntValue extends Value {

    private Integer value = null;

    void setIntValue(int value) {
        this.value = value;
    }

    Integer getIntValue() {
        return value;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }
}
