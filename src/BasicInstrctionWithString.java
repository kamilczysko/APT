import java.util.List;

public class BasicInstrctionWithString implements Record {

    String mainWord;
    String param;

    @Override
    public String getMainWord() {
        return mainWord;
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

    public String getParameter() {
        return param;
    }
}
