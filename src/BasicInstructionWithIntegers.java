import java.util.LinkedList;
import java.util.List;

public class BasicInstructionWithIntegers implements Record {

    String majorWord;
    List<Integer> parameters = new LinkedList<>();


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
