import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ComplexValue extends Value {

    private Stack<Value> parametersStack;

    public void setParametersStack(List<Value> list){
        parametersStack = new Stack();
        Collections.reverse(list);
        parametersStack.addAll(list);
    }

    public Value getNextValue(){
        return parametersStack.pop();
    }

    @Override
    boolean hasBasicValue() {
        return sizeOfArrayOfParametersList() > 0;
    }

    @Override
    public String toString() {
        String vals = "";

        if(hasWord())
            vals += "----"+getWord()+"\n";
        for(Value v : getParameters()){

            if(v instanceof ComplexValue)
                vals += "\t";
            vals += v+"\n";
        }
        return vals;
    }
}
