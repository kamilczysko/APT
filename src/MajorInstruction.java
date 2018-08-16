import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MajorInstruction implements Record{

    private String mainWord = null;
    private List<String> mainWords = new LinkedList<String>();

    private List<Record> parameters;
    private Map<String, Record> params;

    @Override
    public String getMainWord() {
        return null;
    }

    @Override
    public void setMainWord(String mainWord) {
        this.mainWord = mainWord;
        this.mainWords.add(mainWord);
    }

    public void addAnotherMainWord(String mainWord){
        this.mainWords.add(mainWord);
    }

    public boolean hasMainWord(){
        return Optional.ofNullable(mainWord).isPresent();
    }

    @Override
    public void addParameter(Record params) {
        parameters.add(params);
    }

    @Override
    public List getParameters() {
        return null;
    }

//    public static void main(String args[]){
//        MajorInstruction i = new MajorInstruction();
//        i.setMainWord("dupa");
//        System.out.println(i.hasMainWord());
//    }
}
