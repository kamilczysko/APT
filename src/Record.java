import java.util.List;

public interface Record {

    String getMainWord();

    void setMainWord(String mainWord);

    void addParameter(Record record);

    List getParameters();

}
