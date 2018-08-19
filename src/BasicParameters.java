import java.util.List;
import java.util.Map;

public interface BasicParameters {

    BasicParameters addParameter(String param);
    List getParameters();
    Map<String, BasicParameters> getBasicParameters();
    void addBasicParameter(BasicParameters params);
    BasicParameters setWord(String word);
    String getWord();
    boolean hasMajWord();
}
