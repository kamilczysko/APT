
import java.util.*;

public class MajorInstruction implements Record{

    private String mainWord = null;
    private Map<String, BasicParameters> params = new LinkedHashMap<>();
    private List<BasicParameters> instructionParameters = new LinkedList<>();

    @Override
    public String getMainWord() {
        return mainWord;
    }

    @Override
    public void setMainWord(String mainWord) {
        this.mainWord = mainWord;

    }

    public boolean hasMainWord(){
        return Optional.ofNullable(mainWord).isPresent();
    }

    @Override
    public void addParameter(Record params) {

    }

    public void addParameter(BasicParameters parameters) {
        String key = Optional.ofNullable(parameters.getWord()).orElse(mainWord);
        params.put(key, parameters);
    }

    public void addParameter(String key, BasicParameters parameters) {
        params.put(key, parameters);
    }


    public void addParameterToList(BasicParameters parameters) {
        instructionParameters.add(parameters);
    }

    @Override
    public List getParams() {
        return instructionParameters;
    }

    public BasicParameters getParameter(String key) {
        return params.get(key);
    }

    @Override
    public String toString() {
        return "-"+getMainWord()+" \n"+instructionParameters;
    }
}
