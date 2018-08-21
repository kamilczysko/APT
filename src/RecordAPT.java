import java.util.*;

public class RecordAPT {



    public Value createRecord(String record){
        List<String> tableOfRecordItems = getTableOfRecordItems(record);
        ListIterator<String> iterator = tableOfRecordItems.listIterator();
        Value apt = new ComplexValue();
        processListOfParameters(iterator, apt);
        return apt;
    }

    private Value processListOfParameters(ListIterator<String> iterator, Value mainValue){

        while(iterator.hasNext()){
            String next = iterator.next();

            if(next.equals("("))
                mainValue.addParameter(processListOfParameters(iterator, new ComplexValue()));
            else if(next.equals(")")) {
                ((ComplexValue)mainValue).setParametersStack();
                return mainValue;
            }else if(next.isEmpty())
                mainValue.addParameter(null);
            else
                if(isWord(next)) {
                    if (mainValue.hasWord()) {
                        mainValue.addParameter(new BasicStringValue(next));
                    } else
                        mainValue.setWord(next);
                } else if (isDecimal(next))
                    mainValue.addParameter(new BasicDoubleValue(next));
                else if(isInt(next))
                    mainValue.addParameter(new BasicIntValue(next));
        }
        ((ComplexValue)mainValue).setParametersStack();
        return mainValue;
    }

    private List<String> getTableOfRecordItems(String apt){

        List<String> recordList = new LinkedList<>();

        String[] split = apt.split(",|/");

        for(String part : split)
            recordList.addAll(splitRecordPart(part));

        return recordList;
    }

    private List<String> splitRecordPart(String part){
        String a = part.replaceAll("(\\s+)|(\\$+)","");
        return Arrays.asList(a.split("(?<=\\(|\\))|(?=\\(|\\))"));
    }

    private static boolean isDecimal(String word) {
        return word.matches("[-]?[0-9]+[.]+[0-9]+");
    }

    private static boolean isInt(String word) {
        return word.matches("[-]?[0-9]*");
    }

    private boolean isWord(String word){
        return word.matches("[A-Z]*");
    }


    /*****/
    public static void main(String[] args) {
        String txt ="TLON,GOFWD/      (CIRCLE/     -5.00000,     65.00000,     50.00000,$\n" +
                "      15.00000),ON,(LINE/     -5.00000,     65.00000,     50.00000,$\n" +
                "                              -5.00000,(A/ 1,2,3)     ,50.00000,     50.00000),1,2,3";

//                String txt = "GOTO/ 1.234,4.2,5.6,7.4";
//
        RecordAPT r = new RecordAPT();
        Value record = r.createRecord(txt);
        String s = drawRecord(((ComplexValue) record), "");
        System.out.println(record.getWord()+"::\n"+s);
    }


    static String drawRecord(ComplexValue record, String tabs){
        tabs += "\t";
        StringBuilder output = new StringBuilder();
        List<Value> parameters = record.getParameters();
        for(Value myRecord : parameters){
            if(myRecord instanceof ComplexValue) {
                output.append(tabs + myRecord.getWord() + " : \n");
                String s = drawRecord((ComplexValue) myRecord, tabs);
                output.append(s);
            }else
                if(myRecord.hasWord())
                    output.append(myRecord.getWord()+" : \n");
                else if(!(myRecord instanceof ComplexValue))
                    output.append(tabs+"*"+myRecord.toString()+" \n");

        }
        return output.toString();
    }

}
