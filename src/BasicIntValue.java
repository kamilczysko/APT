import java.util.Optional;

public class BasicIntValue extends Value {

    private Integer value = null;

    public BasicIntValue(String value) {
        setIntValue(value);
    }

    public BasicIntValue() {
    }

    void setIntValue(String value) {
        this.value = Integer.parseInt(value);
    }

    Integer getIntValue() {
        return value;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }

    @Override
    public String toString() {
        return getIntValue()+" --- ";
    }
}
