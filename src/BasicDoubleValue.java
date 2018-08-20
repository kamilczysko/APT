import java.util.Optional;

public class BasicDoubleValue extends Value {

    private Double value = null;

    void setDoubleValue(double value) {
        this.value = value;
    }

    Double getDoubleValue() {
        return value;
    }

    @Override
    boolean hasBasicValue() {
        return Optional.ofNullable(value).isPresent();
    }
}
