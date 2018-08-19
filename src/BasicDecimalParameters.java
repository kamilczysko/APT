import javax.swing.text.html.Option;
import java.util.*;

public class BasicDecimalParameters implements BasicParameters {

    private String word;
    private List<Double> parameters = new LinkedList<>();
    private Map<String, BasicParameters> basicParameters = new LinkedHashMap<>();

    @Override
    public BasicParameters addParameter(String param) {
        parameters.add(Double.parseDouble(param));
        return this;
    }

    public List<Double> getParameters(){
        return parameters;
    }

    @Override
    public Map<String, BasicParameters> getBasicParameters() {
        return basicParameters;
    }

    @Override
    public void addBasicParameter(BasicParameters param) {
        String key = Optional.ofNullable(param.getWord()).orElse(word);
        basicParameters.put(key, param);
    }

    public BasicParameters getParamFromMap(String key){
        return basicParameters.get(key);
    }

    @Override
    public BasicParameters setWord(String word) {
        this.word = word;
        return this;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public boolean hasMajWord() {
        return Optional.ofNullable(word).isPresent();
    }

    @Override
    public String toString() {
        String string = "\t"+word+ "-"+parameters.toString();
        Set<String> keys = basicParameters.keySet();
        for(String k : keys)
            string += "\n\t\t - "+basicParameters.get(k);
        return string;
    }
}
