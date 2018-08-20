public class ComplexValue extends Value {
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
