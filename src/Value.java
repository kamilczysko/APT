import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class Value {

    private List<Value> complexParameters = new LinkedList<>();
    private String word = null;

    public void addParameter(Value parameter){
        this.complexParameters.add(parameter);
    }

    public List<Value> getComplexParameters(){
        return complexParameters;
    }

    public Value getComplexValue(int i){
        return complexParameters.get(i);
    }

    public void setWord(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public boolean hasWord(){
        return Optional.ofNullable(word).isPresent();
    }

    abstract boolean hasBasicValue();

}
