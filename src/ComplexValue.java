import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ComplexValue extends Value {
    private Stack<Value> stackOfParameters;

    public void setParametersStack(){
        stackOfParameters = new Stack();
        List<Value> listOfValues = new LinkedList<>(getParameters());

        Collections.reverse(listOfValues);
        stackOfParameters.addAll(listOfValues);
    }

    public Value getNextValue(){
        return (stackOfParameters.size() > 0) ? stackOfParameters.pop() : null;
    }

    @Override
    boolean hasBasicValue() {
        return sizeOfArrayOfParametersList() > 0;
    }
}
