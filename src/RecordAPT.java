import java.util.*;

public class RecordAPT {


    private List<BasicParameters> paramList;

    public List<String> getStackFromRecord(String apt){

        List<String> strings = Arrays.asList(splitRecord(apt));
        System.out.println(strings);
        return strings;
    }

    private String[] splitRecord(String apt){

        String inputRecord = apt.replaceAll("(\\s+)|(\\$+)","");
        String[] split = inputRecord.split("(,)|(?<=/|\\(|\\))|(?=/|\\(|\\))");

        return split;
    }

    private Record generateRecord(String apt){
        List<String> stackFromRecord = getStackFromRecord(apt);
        ListIterator<String> stringListIterator = stackFromRecord.listIterator();
        paramList = new LinkedList<>();
        Record aptRecord = null;

        aptRecord = new MajorInstruction();


        makeSimpleRecord(aptRecord, stringListIterator);

        return aptRecord;
    }

    private void makeSimpleRecord(Record apt, ListIterator<String> listIterator){
        if(listIterator.hasNext()) {

            String next = listIterator.next();

            if (isWord(next)){
                apt.setMainWord(next);
                substrackParameters(listIterator, apt, null);
            }

        }
    }

    private void substrackParameters(ListIterator<String> paramsIterator, Record apt, BasicParameters basicParameters){

        String majWord = null;
        BasicParameters parameters = null;

        while(paramsIterator.hasNext()){
            String next = paramsIterator.next();
            if(next.equals("BC"))
                System.out.println("asdd");

            if(next.equals("/") || next.equals(""))
                continue;
            else if(next.equals("(")){
                substrackParameters(paramsIterator,apt, parameters);
            }else if(next.equals(")")){
                basicParameters.addBasicParameter(parameters);
                if(basicParameters != null)
                return ;
                else continue;
            }

            if(isWord(next)) {
                if (majWord == null) {
                    majWord = next;
                    if(basicParameters == null)
                        ((MajorInstruction)apt).addParameterToList(parameters);
                    else
                        basicParameters.addBasicParameter(new BasicStringParameter().setWord(majWord));
                    parameters = null;
                }else{
                    if(basicParameters != null){
                        if(parameters != null)
                            basicParameters.addBasicParameter(parameters);
                        else
                            basicParameters.addBasicParameter(new BasicStringParameter().setWord(majWord));
                        parameters = null;
                    }
                    majWord = next;

                    continue;
                }
            }else if(isDecimal(next)){
                if(parameters != null){
                    if(paramatersTypeHasChanged(parameters, new BasicDecimalParameters())){
                        if(!parameters.hasMajWord() && majWord != null)
                            parameters.setWord(majWord);
                        if(basicParameters == null)
                            ((MajorInstruction)apt).addParameterToList(parameters);
                        else
                            basicParameters.addBasicParameter(parameters);

                        majWord = null;
                        parameters = new BasicDecimalParameters();
                    }
                }else {
                    parameters = new BasicDecimalParameters();
                    parameters.setWord(majWord);
                }

                parameters.addParameter(next);
                System.out.println(parameters);

            }else if(isInt(next)){
                if(parameters != null){
                    if(paramatersTypeHasChanged(parameters, new BasicIntParameters())){
                        if(!parameters.hasMajWord() && majWord != null)
                            parameters.setWord(majWord);
                        if(basicParameters == null)
                            ((MajorInstruction)apt).addParameterToList(parameters);
                        else
                            basicParameters.addBasicParameter(parameters);
                        majWord = null;
                        parameters = new BasicIntParameters();
                    }
                }else {
                    parameters = new BasicIntParameters();
                    parameters.setWord(majWord);
                }

                parameters.addParameter(next);

                System.out.println(parameters);
            }
        }
        if(parameters != null) {
            if (majWord != null && parameters == null)
                parameters.addBasicParameter(new BasicStringParameter().setWord(majWord));
            else {
                if(majWord != null)
                    parameters.setWord(majWord);
                ((MajorInstruction) apt).addParameterToList(parameters);
            }
        }
    }


    private boolean paramatersTypeHasChanged(Object params, Object instance){
        return params!=null && !(params.getClass().isAssignableFrom(instance.getClass()));
    }
    
    protected static boolean isDecimal(String word) {
        return word.matches("[-]?[0-9]+[.]+[0-9]+");
    }

    protected static boolean isInt(String word) {
        return word.matches("[-]?[0-9]*");
    }

    private boolean isWord(String word){
        return word.matches("[A-Z]*");
    }

    public static void main(String[] args) {
//        String txt ="TLON,GOFWD/      (CIRCLE/     -5.00000,     65.00000,     50.00000,$\n" +
//                "      15.00000),ON,(LINE/     -5.00000,     65.00000,     50.00000,$\n" +
//                "                              -5.00000,     50.00000,     50.00000)";

                String txt = "CIRCLE/     50.00000, 1 , 1.4, A, 1, (B, 1,2,C, 2,3,(D,2.34)),BC,2";


        RecordAPT r = new RecordAPT();
        Record record = r.generateRecord(txt);
        System.out.println(record.getMainWord());
        List<BasicParameters> params = ((MajorInstruction)record).getParams();
        for(BasicParameters p : params)
            System.out.println(p);


    }

}
