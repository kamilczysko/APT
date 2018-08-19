import javax.swing.text.html.Option;
import java.util.*;

public class BasicStringParameter implements BasicParameters {

    private String word = null;
    private List<String> stringParam = new LinkedList<>();
    private Map<String, BasicParameters> basicParameters = new LinkedHashMap<>();

    @Override
    public BasicParameters addParameter(String param) {
        stringParam.add(param);
        return this;
    }

    @Override
    public List getParameters() {
        return stringParam;
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
        return getParameters().toString()+" - "+word;
    }
}
