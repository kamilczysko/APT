import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ComplexValue extends Value {

    private Stack<Value> parametersStack;

    public void setParametersStack(){
        parametersStack = new Stack();
        List<Value> vals = new LinkedList<>(getParameters());

        Collections.reverse(vals);
        parametersStack.addAll(vals);
    }

    public Value getNextValue(){
        return (parametersStack.size() > 0) ? parametersStack.pop() : null;
    }

    @Override
    boolean hasBasicValue() {
        return sizeOfArrayOfParametersList() > 0;
    }
}
