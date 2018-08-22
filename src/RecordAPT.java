import java.util.*;

public class RecordAPT {

    public Value getComplexValueFromString(String record){
        List<String> listOfRecordStrings = splitRecordToTableOfStrings(record);
        ListIterator<String> iterator = listOfRecordStrings.listIterator();
        Value apt = new ComplexValue();

        convertRecordToComplexValue(iterator, apt);
        return apt;
    }

    private Value convertRecordToComplexValue(ListIterator<String> iterator, Value mainValue){

        while(iterator.hasNext()){
            String next = iterator.next();

            if(next.equals("("))
                mainValue.addParameter(convertRecordToComplexValue(iterator, new ComplexValue()));
            else if(next.equals(")")) {
                ((ComplexValue)mainValue).setParametersStack();
                return mainValue;
            }else if(next.isEmpty())
                mainValue.addParameter(null);
            else
                assignAppropriateValues(next, mainValue);
        }
        ((ComplexValue)mainValue).setParametersStack();
        return mainValue;
    }

    private void assignAppropriateValues(String next, Value mainValue){
        if(isWord(next)) {
            if (mainValue.hasWord())
                mainValue.addParameter(new BasicStringValue(next));
            else
                mainValue.setWord(next);
        } else if (isDecimal(next))
            mainValue.addParameter(new BasicDoubleValue(next));
        else if(isInt(next))
            mainValue.addParameter(new BasicIntValue(next));
    }

    private List<String> splitRecordToTableOfStrings(String apt){
        List<String> recordList = new LinkedList<>();

        String[] split = apt.split(",|/");
        for(String part : split)
            recordList.addAll(splitOnePieceOfRecord(part));

        return recordList;
    }

    private List<String> splitOnePieceOfRecord(String part){
        String a = part.replaceAll("(\\s+)|(\\$+)","");
        return Arrays.asList(a.split("(?<=\\(|\\))|(?=\\(|\\))"));
    }

    private boolean isDecimal(String word) {
        return word.matches("[-]?[0-9]+[.]+[0-9]+");
    }

    private boolean isInt(String word) {
        return word.matches("[-]?[0-9]*");
    }

    private boolean isWord(String word){
        return word.matches("[A-Z]*");
    }


    /*****/
    public static void main(String[] args) {
        String txt ="TLON,GOFWD/      (CIRCLE/     -5.00000,     65.00000,     50.00000,$\n" +
                "      15.00000),ON,(LINE/     -5.00000,     65.00000,     50.00000,$\n" +
                "                              -5.00000, ,51.00000,     50.00000)";

        RecordAPT r = new RecordAPT();
        Value record = r.getComplexValueFromString(txt);
        String s = record.getWord()+"::\n"+drawRecord(((ComplexValue) record), "");
        System.out.println(s);
    }


    static String drawRecord(ComplexValue record, String tabs){
        tabs += "\t";
        StringBuilder output = new StringBuilder();
        List<Value> parameters = record.getParameters();
        for(Value myRecord : parameters){
            if(myRecord instanceof ComplexValue) {
                output.append(tabs + "-"+myRecord.getWord() + " : \n");
                String s = drawRecord((ComplexValue) myRecord, tabs);
                output.append(s);
            }else if(myRecord == null) {
                output.append(tabs+"none---\n");
            }else
                if(myRecord.hasWord())
                    output.append(myRecord.getWord()+" : \n");
                else if(!(myRecord instanceof ComplexValue))
                    output.append(tabs+"*"+myRecord.toString()+" \n");

        }
        return output.toString();
    }

}
