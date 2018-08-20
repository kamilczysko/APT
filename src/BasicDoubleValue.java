import java.util.Optional;

public class BasicDoubleValue extends Value {

    private Double value = null;

    public BasicDoubleValue(String value) {
        setDoubleValue(value);
    }

    public BasicDoubleValue() {
    }

    void setDoubleValue(String value) {
        this.value = Double.parseDouble(value);
    }

    Double getDoubleValue() {
        return value;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }

    @Override
    public String toString() {
        return getDoubleValue()+" --- ";
    }
}
