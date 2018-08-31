import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ComplexValue extends Value {
    private Stack<Value> stackOfParameters;

    public void setParametersStack(){
        stackOfParameters = new Stack();
        List<Value> list = new LinkedList<>(getParameters());

        Collections.reverse(list);
        stackOfParameters.addAll(list);
    }

    public Value getNextValue(){
        return (stackOfParameters.size() > 0) ? stackOfParameters.pop() : null;
    }

    @Override
    boolean hasBasicValue() {
        return sizeOfArrayOfParametersList() > 0;
    }
}
