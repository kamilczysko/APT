import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ComplexValue extends Value {
    private Stack<Value> parametersStack;

    public void setParametersStack(){
        parametersStack = new Stack();
        List<Value> listOfValues = new LinkedList<>(getParameters());

        Collections.reverse(listOfValues);
        parametersStack.addAll(listOfValues);
    }

    public Value getNextValue(){
        return (parametersStack.size() > 0) ? parametersStack.pop() : null;
    }

    @Override
    boolean hasBasicValue() {
        return sizeOfArrayOfParametersList() > 0;
    }
}
