import java.util.List;
import java.util.Map;

public class MajorInstruction implements Record{

    private String mainWord;
    private List parameters;
    private Map<String, Record> params;

    @Override
    public String getMainWord() {
        return null;
    }

    @Override
    public void setMainWord(String mainWord) {

    }

    @Override
    public void addParameter() {

    }

    @Override
    public List getParameters() {
        return null;
    }
}
