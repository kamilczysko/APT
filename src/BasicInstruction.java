import java.util.LinkedList;
import java.util.List;

public class BasicInstruction implements Record{

    private String mainWord;
    private List<BasicParameters> parameters = new LinkedList<>();

    @Override
    public String getMainWord() {
        return this.mainWord;
    }

    @Override
    public void setMainWord(String mainWord) {
        this.mainWord = mainWord;
    }

    @Override
    public void addParameter(Record record) {

    }

    public void addParameter(BasicParameters parameters) {
        this.parameters.add(parameters);
    }

    @Override
    public List getParameters() {
        return this.parameters;
    }
}
