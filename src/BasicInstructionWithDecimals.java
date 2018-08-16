import java.util.LinkedList;
import java.util.List;

public class BasicInstructionWithDecimals implements Record {

    String majorWord;
    List<BasicParameters> parameters = new LinkedList<>();


    @Override
    public String getMainWord() {
        return null;
    }

    @Override
    public void setMainWord(String mainWord) {

    }

    @Override
    public void addParameter(Record record) {

    }

    @Override
    public List getParameters() {
        return null;
    }
}
